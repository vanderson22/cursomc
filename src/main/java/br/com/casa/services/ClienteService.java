package br.com.casa.services;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.casa.dominio.Cidade;
import br.com.casa.dominio.Cliente;
import br.com.casa.dominio.Endereco;
import br.com.casa.dominio.DTO.ClienteDTO;
import br.com.casa.dominio.DTO.ClienteNewDTO;
import br.com.casa.dominio.enums.TipoCliente;
import br.com.casa.exceptions.DataIntegridadeException;
import br.com.casa.exceptions.ObjectNotFoundException;
import br.com.casa.repositories.CidadeRepository;
import br.com.casa.repositories.ClienteRepository;
import br.com.casa.repositories.EnderecoRepository;

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

	public Cliente buscar(Integer id) throws ObjectNotFoundException {

		Optional<Cliente> optional = repo.findById(id);

		return optional.orElseThrow(
				() -> new ObjectNotFoundException("Cliente não encontrado  - identificador :[" + id + "]"));

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

		return new Cliente(dto.getId(), dto.getNome(), dto.getEmail(), null, null)
				.senha(null);
	}

//	@Transactional

	public Cliente fromDTO(ClienteNewDTO clienteNEW) {

		Cliente cliente = new Cliente(null, clienteNEW.getNome(), clienteNEW.getEmail(), clienteNEW.getCpfCnpj(),
				TipoCliente.toEnum(clienteNEW.getTipo()))
				                  .senha(encoder.encode(clienteNEW.getSenha()));

		Cidade cidade = cidadeRepo.findById(clienteNEW.getCidadeId()).orElseThrow(() -> new ObjectNotFoundException(
				"Cidade não encontrada  - identificador :[" + clienteNEW.getCidadeId() + "]"));

		Endereco end = new Endereco(null, clienteNEW.getLogradouro(), clienteNEW.getNumero(),
				clienteNEW.getComplemento(), clienteNEW.getBairro(), clienteNEW.getCep(), cliente, cidade);

		cliente.getEnderecos().add(end);

		Arrays.asList(clienteNEW.getTelefone1(), clienteNEW.getTelefone2(), clienteNEW.getTelefone3())
				.forEach(tel -> cliente.getTelefones().add(tel));

		return cliente;
	}

	public Cliente criar(Cliente cli) {
		Cliente cliente = repo.save(cli);
		enderecoRepo.saveAll(cliente.getEnderecos());
		return cliente;

	}

}
