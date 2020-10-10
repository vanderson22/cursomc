package br.com.casa.resources;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.casa.dominio.Categoria;
import br.com.casa.dominio.DTO.CategoriaDTO;
import br.com.casa.services.CategoriaService;

/**
 * Recurso  -  Categoria
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

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<CategoriaDTO>> buscarTodos() {

		// UTILIZANDO O PADRÃO DTO - motivo ? não retornar todos os dados da categoria Exemplo Produtos
		List<Categoria> buscarTodos = catService.buscarTodos();
		List<CategoriaDTO> listaDTO = new ArrayList<CategoriaDTO>();
		
		buscarTodos.forEach(cat -> listaDTO.add(new CategoriaDTO(cat)));
		  
		return ResponseEntity.ok().body(listaDTO);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Categoria> buscar(@PathVariable Integer id) {
		// Handler para interceptar erros
		return ResponseEntity.ok().body(catService.buscar(id));
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> criar(@RequestBody Categoria categoria) throws URISyntaxException {
		Categoria catCriada = catService.criar(categoria);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(catCriada.getId())
				.toUri();
		// return ResponseEntity.ok().body(catService.buscar(categoria));
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> atualizar(@PathVariable Integer id, @RequestBody Categoria categoria)
			throws URISyntaxException {

		categoria.setId(id);
		catService.update(categoria);

		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> atualizar(@PathVariable Integer id) throws URISyntaxException {

		catService.deletar(id);

		return ResponseEntity.noContent().build();
	}

}
