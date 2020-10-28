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

import br.com.casa.dominio.Categoria;
import br.com.casa.dominio.DTO.CategoriaDTO;
import br.com.casa.services.CategoriaService;

/**
 * Recurso - Categoria
 * 
 * [Controlador Rest ]
 * 
 * 
 * Os métodos não possuem try Catch, o tratamento é feito por um handler que
 * captura as exceções que seriam devolvidas ao cliente e formata da melhor
 * maneira possível.
 ***/

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {
	@Autowired
	private CategoriaService catService;

	/**
	 * 
	 * @return Lista de objeto Categoria DTO.
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<CategoriaDTO>> buscarTodos() {
		List<Categoria> buscarTodos = catService.buscarTodos();
		List<CategoriaDTO> listaDTO = new ArrayList<CategoriaDTO>();

		buscarTodos.forEach(cat -> listaDTO.add(new CategoriaDTO(cat)));

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
	public ResponseEntity<Page<CategoriaDTO>> buscaPaginada(
			@RequestParam(value = "pagina", defaultValue = "0") Integer pagina,
			@RequestParam(value = "quantidadeLinha", defaultValue = "24") Integer quantidadeLinha,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "ascendeteOuDescente", defaultValue = "ASC") String direction) {

		Page<Categoria> page = catService.buscaPaginada(pagina, quantidadeLinha, orderBy, direction);
		Page<CategoriaDTO> listaDTO = page.map(obj -> new CategoriaDTO(obj));

		return ResponseEntity.ok().body(listaDTO);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Categoria> buscar(@PathVariable Integer id) {
		// sem try cat - usa Handler para interceptar erros
		return ResponseEntity.ok().body(catService.buscar(id));
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> criar(@Valid @RequestBody CategoriaDTO categoria) throws URISyntaxException {
		// Para validar

		Categoria catCriada = catService.criar(catService.fromDTO(categoria));
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(catCriada.getId())
				.toUri();
		// return ResponseEntity.ok().body(catService.buscar(categoria));
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> atualizar(@PathVariable Integer id, @Valid @RequestBody CategoriaDTO categoriaDTO)
			throws URISyntaxException {

		categoriaDTO.setId(id);
		catService.update(catService.fromDTO(categoriaDTO));

		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> atualizar(@PathVariable Integer id) throws URISyntaxException {

		catService.deletar(id);

		return ResponseEntity.noContent().build();
	}

}
