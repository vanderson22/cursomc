package br.com.casa.resources;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.casa.dominio.Categoria;
import br.com.casa.services.CategoriaService;

/**
 * Recurso de Categoria
 * 
 * Camada de controler rest
 ***/

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {
	@Autowired
	private CategoriaService catService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> buscar(@PathVariable Integer id) {
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
