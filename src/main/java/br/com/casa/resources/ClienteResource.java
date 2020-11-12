package br.com.casa.resources;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.casa.dominio.Cliente;
import br.com.casa.dominio.DTO.ClienteDTO;
import br.com.casa.dominio.DTO.ClienteNewDTO;
import br.com.casa.services.ClienteService;

/**
 * Recurso de Cliente
 * 
 * Camada de controler rest
 ***/

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {
	@Autowired
	private ClienteService clienteService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Cliente> buscar(@PathVariable Integer id) {
		return ResponseEntity.ok().body(clienteService.buscar(id));
	}

	@RequestMapping(value = "/email", method = RequestMethod.GET)
	public ResponseEntity<Cliente> buscar(
			@RequestParam(value = "email", required = true, name = "email") String email) {

		return ResponseEntity.ok().body(clienteService.buscarPorEmail(email));
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ClienteDTO>> buscarTodos() {
		List<Cliente> buscarTodos = clienteService.buscarTodos();
		List<ClienteDTO> listaDTO = new ArrayList<ClienteDTO>();

		buscarTodos.forEach(cat -> listaDTO.add(new ClienteDTO(cat)));

		return ResponseEntity.ok().body(listaDTO);
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ResponseEntity<Page<ClienteDTO>> buscaPaginada(
			@RequestParam(value = "pagina", defaultValue = "0") Integer pagina,
			@RequestParam(value = "quantidadeLinha", defaultValue = "24") Integer quantidadeLinha,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "ascendeteOuDescente", defaultValue = "ASC") String direction) {

		Page<Cliente> page = clienteService.buscaPaginada(pagina, quantidadeLinha, orderBy, direction);
		Page<ClienteDTO> listaDTO = page.map(obj -> new ClienteDTO(obj));

		return ResponseEntity.ok().body(listaDTO);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	// Atenção neste ponto! o @Valid ocorre antes de setar o id, então como validar
	// o e-mail ?
	// Validador customizado capturando o httpRequest - ID - Cliente Update
	// Validator
	public ResponseEntity<Void> atualizar(@PathVariable Integer id, @Valid @RequestBody ClienteDTO clienteDTO) {
		clienteDTO.setId(id);
		clienteService.update(clienteService.fromDTO(clienteDTO));

		return ResponseEntity.noContent().build();
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deletar(@PathVariable Integer id) throws URISyntaxException {

		clienteService.deletar(id);

		return ResponseEntity.noContent().build();
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> criar(@Valid @RequestBody ClienteNewDTO clienteNEW) throws URISyntaxException {
		// Para validar
		Cliente cliente = clienteService.criar(clienteService.fromDTO(clienteNEW));
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cliente.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}

	
	/***
	 *  Faz upload da imagem com seguinte formato : 
	 *     cp'id'.jpg
	 * */
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public ResponseEntity<Void> upload(@RequestParam(name = "arquivo") MultipartFile arquivo) {

		URI uri = clienteService.uploadProfilePicture(arquivo);

		return ResponseEntity.created(uri).build();
	}
}
