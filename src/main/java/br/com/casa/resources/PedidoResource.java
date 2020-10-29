package br.com.casa.resources;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.casa.dominio.Pedido;
import br.com.casa.services.PedidoService;

/**
 * Recurso de Pedido
 * 
 * Camada de controler rest
 ***/

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoResource {
	@Autowired
	private PedidoService pedidoService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> buscar(@PathVariable Integer id) {
		// Handler para interceptar erros
		return ResponseEntity.ok().body(pedidoService.buscar(id));
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> buscarTodos() {
		// Handler para interceptar erros
		return ResponseEntity.ok().body(pedidoService.buscar());
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> criar(@RequestBody Pedido pedido) throws URISyntaxException {
		// Para validar

		Pedido criado = pedidoService.criar(pedido);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(criado.getId()).toUri();
		// return ResponseEntity.ok().body(catService.buscar(categoria));
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ResponseEntity<Page<Pedido>> buscaPaginada(
			@RequestParam(value = "pagina", defaultValue = "0") Integer pagina,
			@RequestParam(value = "quantidadeLinha", defaultValue = "24") Integer quantidadeLinha,
			@RequestParam(value = "orderBy", defaultValue = "instante") String orderBy,
			@RequestParam(value = "ascendeteOuDescente", defaultValue = "DESC") String direction) {

		Page<Pedido> pedidos = pedidoService.buscaPaginada(pagina, quantidadeLinha, orderBy, direction);

		return ResponseEntity.ok().body(pedidos);
	}

}
