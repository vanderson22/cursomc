package br.com.casa.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.casa.dominio.Cidade;
import br.com.casa.dominio.Estado;
import br.com.casa.dominio.DTO.EstadoDTO;
import br.com.casa.exceptions.ObjectNotFoundException;
import br.com.casa.services.CidadeService;
import br.com.casa.services.EstadoService;

/**
 * Recurso de Pedido
 * 
 * Camada de controler rest
 ***/

@RestController
@RequestMapping(value = "/estados")
public class EstadoResource {
	@Autowired
	private EstadoService estadoService;

	@Autowired
	private CidadeService cidadeService;

	private static final Logger log = LoggerFactory.getLogger(EstadoResource.class);

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<EstadoDTO>> buscar() {

		List<Estado> list = estadoService.buscarTodos();

		if (list == null)
			throw new ObjectNotFoundException("Não foi possível encontrar lista de estados");

		List<EstadoDTO> estados = list.stream().map(estado -> new EstadoDTO(estado.getId(), estado.getNome()))
				.collect(Collectors.toList());

		return ResponseEntity.ok().body(estados);
	}

	@RequestMapping(value = "/{estado_id}/cidades", method = RequestMethod.GET)
	public ResponseEntity<List<EstadoDTO>> buscarEstado(@PathVariable(value = "estado_id") Integer estadoId) {
		log.info("Iniciada requisição para buscar o estado [" + estadoId + "]");

		// não precisava dar new Estado poderia buscar por @Query
		List<Cidade> list = cidadeService.buscarCidadePorEstado(new Estado(estadoId, null));

		if (list == null)
			throw new ObjectNotFoundException("Não foi possível encontrar o estado informado");

		List<EstadoDTO> estados = list.stream().map(estado -> new EstadoDTO(estado.getId(), estado.getNome()))
				.collect(Collectors.toList());

		return ResponseEntity.ok().body(estados);
	}
//	
//	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
//	public ResponseEntity<?> buscar(@RequestParam String nome) {
//		// Handler para interceptar erros
//		return ResponseEntity.ok().body(estadoService.buscar(id));
//	}
//
//	@RequestMapping(method = RequestMethod.GET)
//	public ResponseEntity<?> buscarTodos() {
//		// Handler para interceptar erros
//		return ResponseEntity.ok().body(estadoService.buscar());
//	}

//	@RequestMapping(method = RequestMethod.POST)
//	public ResponseEntity<Void> criar(@RequestBody Pedido pedido) throws URISyntaxException {
//		// Para validar
//
//		Pedido criado = estadoService.criar(pedido);
//		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(criado.getId()).toUri();
//		// return ResponseEntity.ok().body(catService.buscar(categoria));
//		return ResponseEntity.created(uri).build();
//	}
//
//	@RequestMapping(value = "/page", method = RequestMethod.GET)
//	public ResponseEntity<Page<Pedido>> buscaPaginada(
//			@RequestParam(value = "pagina", defaultValue = "0") Integer pagina,
//			@RequestParam(value = "quantidadeLinha", defaultValue = "24") Integer quantidadeLinha,
//			@RequestParam(value = "orderBy", defaultValue = "instante") String orderBy,
//			@RequestParam(value = "ascendeteOuDescente", defaultValue = "DESC") String direction) {
//
//		Page<Pedido> pedidos = estadoService.buscaPaginada(pagina, quantidadeLinha, orderBy, direction);
//
//		return ResponseEntity.ok().body(pedidos);
//	}

}
