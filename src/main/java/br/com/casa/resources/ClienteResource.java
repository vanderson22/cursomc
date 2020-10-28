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
	private ClienteService catService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> buscar(@PathVariable Integer id) {
		// Handler para interceptar erros
		return ResponseEntity.ok().body(catService.buscar(id));
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ClienteDTO>> buscarTodos() {
		List<Cliente> buscarTodos = catService.buscarTodos();
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

		Page<Cliente> page = catService.buscaPaginada(pagina, quantidadeLinha, orderBy, direction);
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
		catService.update(catService.fromDTO(clienteDTO));

		return ResponseEntity.noContent().build();
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> atualizar(@PathVariable Integer id) throws URISyntaxException {

		catService.deletar(id);

		return ResponseEntity.noContent().build();
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> criar(@Valid @RequestBody ClienteNewDTO clienteNEW) throws URISyntaxException {
		// Para validar
		Cliente cliente = catService.criar(catService.fromDTO(clienteNEW));
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cliente.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}

}
