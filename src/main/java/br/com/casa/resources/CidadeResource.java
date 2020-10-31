package br.com.casa.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.casa.dominio.Cidade;
import br.com.casa.dominio.DTO.CidadeDTO;
import br.com.casa.exceptions.ObjectNotFoundException;
import br.com.casa.services.CidadeService;

/**
 * Recurso de Pedido
 * 
 * Camada de controler rest
 ***/

@RestController
@RequestMapping(value = "/cidades")
public class CidadeResource {
	@Autowired
	private CidadeService cidadeService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<CidadeDTO>> buscar() {

		List<Cidade> list = cidadeService.buscarTodos();

		if (list == null)
			throw new ObjectNotFoundException("Não foi possível encontrar lista de cidades");

		List<CidadeDTO> cidades = list.stream()
				.map(cidade -> new CidadeDTO(cidade.getId(), cidade.getNome(), cidade.getEstado()))
				.collect(Collectors.toList());

		return ResponseEntity.ok().body(cidades);
	}
//	
//	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
//	public ResponseEntity<?> buscar(@RequestParam String nome) {
//		// Handler para interceptar erros
//		return ResponseEntity.ok().body(cidadeService.buscar(id));
//	}
//
//	@RequestMapping(method = RequestMethod.GET)
//	public ResponseEntity<?> buscarTodos() {
//		// Handler para interceptar erros
//		return ResponseEntity.ok().body(cidadeService.buscar());
//	}

//	@RequestMapping(method = RequestMethod.POST)
//	public ResponseEntity<Void> criar(@RequestBody Pedido pedido) throws URISyntaxException {
//		// Para validar
//
//		Pedido criado = cidadeService.criar(pedido);
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
//		Page<Pedido> pedidos = cidadeService.buscaPaginada(pagina, quantidadeLinha, orderBy, direction);
//
//		return ResponseEntity.ok().body(pedidos);
//	}

}
