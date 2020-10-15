package br.com.casa.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.casa.dominio.Produto;
import br.com.casa.dominio.DTO.ProdutoDTO;
import br.com.casa.resources.util.URL;
import br.com.casa.services.ProdutoService;

/**
 * Recurso - Produto
 * 
 * [Controlador Rest ]
 * 
 * 
 * Os métodos não possuem try Catch, o tratamento é feito por um handler que
 * captura as exceções que seriam devolvidas ao cliente e formata da melhor
 * maneira possível.
 ***/

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoResource {
	@Autowired
	private ProdutoService catService;

	/**
	 * 
	 * @return Lista de objeto Produto DTO.
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ProdutoDTO>> buscarTodos() {
		List<Produto> buscarTodos = catService.buscarTodos();
		List<ProdutoDTO> listaDTO = new ArrayList<ProdutoDTO>();

		buscarTodos.forEach(cat -> listaDTO.add(new ProdutoDTO(cat)));

		return ResponseEntity.ok().body(listaDTO);
	}

	/**
	 * Busca paginada Pode informar nos request param se é opcional ou não Porém já
	 * está com valores default
	 * 
	 * @return Objeto paginado.
	 * 
	 ***/
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ResponseEntity<Page<ProdutoDTO>> buscaPaginada(@RequestParam(value = "nome", defaultValue = "") String nome,
			@RequestParam(value = "categorias", defaultValue = "0") String categorias,
			@RequestParam(value = "pagina", defaultValue = "0") Integer pagina,
			@RequestParam(value = "quantidadeLinha", defaultValue = "24") Integer quantidadeLinha,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "ascendeteOuDescente", defaultValue = "ASC") String direction) {

		Page<Produto> page = catService.buscaPaginada(URL.decode(nome), URL.converteLista(categorias), pagina, quantidadeLinha, orderBy,
				direction);
		Page<ProdutoDTO> listaDTO = page.map(obj -> new ProdutoDTO(obj));

		return ResponseEntity.ok().body(listaDTO);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Produto> buscar(@PathVariable Integer id) {
		// sem try cat - usa Handler para interceptar erros
		return ResponseEntity.ok().body(catService.buscar(id));
	}

//	@RequestMapping(method = RequestMethod.POST)
//	public ResponseEntity<Void> criar(@Valid @RequestBody ProdutoDTO categoria) throws URISyntaxException {
//		// Para validar
//
//		Produto catCriada = catService.criar(catService.fromDTO(categoria));
//		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(catCriada.getId())
//				.toUri();
//		// return ResponseEntity.ok().body(catService.buscar(categoria));
//		return ResponseEntity.created(uri).build();
//	}
//
//	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
//	public ResponseEntity<Void> atualizar(@PathVariable Integer id, @Valid @RequestBody ProdutoDTO categoriaDTO)
//			throws URISyntaxException {
//
//		categoriaDTO.setId(id);
//		catService.update(catService.fromDTO(categoriaDTO));
//
//		return ResponseEntity.noContent().build();
//	}
//
//	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
//	public ResponseEntity<Void> atualizar(@PathVariable Integer id) throws URISyntaxException {
//
//		catService.deletar(id);
//
//		return ResponseEntity.noContent().build();
//	}

}
