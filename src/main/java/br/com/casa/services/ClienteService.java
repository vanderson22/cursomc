package br.com.casa.services;

import java.awt.image.BufferedImage;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.casa.dominio.Cidade;
import br.com.casa.dominio.Cliente;
import br.com.casa.dominio.Endereco;
import br.com.casa.dominio.DTO.ClienteDTO;
import br.com.casa.dominio.DTO.ClienteNewDTO;
import br.com.casa.dominio.enums.Perfil;
import br.com.casa.dominio.enums.TipoCliente;
import br.com.casa.exceptions.AuthorizationException;
import br.com.casa.exceptions.DataIntegridadeException;
import br.com.casa.exceptions.ObjectNotFoundException;
import br.com.casa.repositories.CidadeRepository;
import br.com.casa.repositories.ClienteRepository;
import br.com.casa.repositories.EnderecoRepository;
import br.com.casa.services.security.DetalhesDeUsuario;
import javassist.NotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;
	@Autowired
	private CidadeRepository cidadeRepo;

	@Autowired
	private EnderecoRepository enderecoRepo;

	@Autowired
	private BCryptPasswordEncoder encoder;
	@Autowired
	private S3Service s3Service;
	@Autowired
	private ImagemService imageService;

	@Value("${s3.prefixo.imagem}")
	private String prefixo;

	private static final Logger log = LoggerFactory.getLogger(ClienteService.class);

	public Cliente buscar(Integer id) throws ObjectNotFoundException {
		// o cliente só pode buscar a si próprio, porém o perfil administrativo pode
		// buscar qualquer id
		DetalhesDeUsuario autenticado = UsuarioService.autenticado();
		if (autenticado == null || !autenticado.hasHole(Perfil.ADMIN) && !id.equals(autenticado.getId())) {

			throw new AuthorizationException(
					"Usuário não possui o perfil de administrador e não pode consultar outros usuários");
		}

		Optional<Cliente> optional = repo.findById(id);

		return optional.orElseThrow(
				() -> new ObjectNotFoundException("Cliente não encontrado  - identificador :[" + id + "]"));

	}

	/**
	 * Realiza a busca no repositório pelo e-mail do usuário, Verifica se o usuário
	 * autenticado é o usuário informado no e-mail
	 ***/
	public Cliente buscarPorEmail(String email) {
		DetalhesDeUsuario autenticado = UsuarioService.autenticado();
		if (autenticado == null || !autenticado.hasHole(Perfil.ADMIN) && !email.equals(autenticado.getUsername())) {

			throw new AuthorizationException(
					"Usuário não possui o perfil de administrador e não pode consultar outros usuários");
		}
		// aqui precisa ser em inglês
		Cliente cliente = repo.findByEmail(email);
		if (cliente == null)
			throw new ObjectNotFoundException("Cliente não encontrado [" + email + "]");

		return cliente;
	}

	public Cliente update(Cliente cliUpdate) {
		Cliente cliente = buscar(cliUpdate.getId());
		// Desta forma pois no DTO - RGN só podemos informar nome e email, e eles são
		// validados pelo hibernate.
		// Esta é uma regra de negocio.

		cliente.setNome(cliUpdate.getNome());
		cliente.setEmail(cliUpdate.getEmail());
		return repo.save(cliente);
	}

	public void deletar(Integer id) {
		try {
			repo.delete(buscar(id));
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegridadeException(
					"Não é possível excluir o cliente, pois ele possui entidades vinculadas - Cliente id [" + id + "]");
		}

	}

	/**
	 * 
	 * Busca paginada
	 */
	public Page<Cliente> buscaPaginada(Integer pagina, Integer quantidadeLinha, String orderBy, String direction) {

		PageRequest pr = PageRequest.of(pagina, quantidadeLinha, Direction.valueOf(direction), orderBy);

		return repo.findAll(pr);
	}

	public List<Cliente> buscarTodos() {

		return repo.findAll();
	}

	public Cliente fromDTO(ClienteDTO dto) {

		return new Cliente(dto.getId(), dto.getNome(), dto.getEmail(), null, null).senha(null);
	}

//	@Transactional

	public Cliente fromDTO(ClienteNewDTO clienteNEW) {

		Cliente cliente = new Cliente(null, clienteNEW.getNome(), clienteNEW.getEmail(), clienteNEW.getCpfCnpj(),
				TipoCliente.toEnum(clienteNEW.getTipo())).senha(encoder.encode(clienteNEW.getSenha()));

		Cidade cidade = cidadeRepo.findById(clienteNEW.getCidadeId()).orElseThrow(() -> new ObjectNotFoundException(
				"Cidade não encontrada  - identificador :[" + clienteNEW.getCidadeId() + "]"));

		Endereco end = new Endereco(null, clienteNEW.getLogradouro(), clienteNEW.getNumero(),
				clienteNEW.getComplemento(), clienteNEW.getBairro(), clienteNEW.getCep(), cliente, cidade);

		cliente.getEnderecos().add(end);

		Arrays.asList(clienteNEW.getTelefone1(), clienteNEW.getTelefone2(), clienteNEW.getTelefone3())
				.forEach(tel -> cliente.getTelefones().add(tel));

		return cliente;
	}

	/**
	 * Salva a URL no cliente e enviar a imagem
	 * 
	 * @throws NotFoundException
	 * 
	 **/
	public URI uploadProfilePicture(MultipartFile mp) {
		DetalhesDeUsuario usuario = UsuarioService.autenticado();
		if (usuario == null)
			throw new AuthorizationException("Acesso Negado");

		URI uri = s3Service.upload(mp);
		Cliente cli = repo.findById(usuario.getId())
				.orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado"));

		BufferedImage buf = imageService.recuperaJPG(mp);

		log.info(" URL da imagem  [" + uri.toString() + "] Cliente [" + cli.getId() + "]");

		String nome = prefixo + "" + cli.getId() + ".jpg";

		return s3Service.upload(imageService.getInputStream(buf, "jpg"), nome, "image");
	}

	public Cliente criar(Cliente cli) {
		Cliente cliente = repo.save(cli);
		enderecoRepo.saveAll(cliente.getEnderecos());
		return cliente;

	}

}
