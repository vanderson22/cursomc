package br.com.casa.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.casa.dominio.Categoria;

/**
 * Recurso de Categoria
 * 
 * 
 ***/

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResources {

	@RequestMapping(method = RequestMethod.GET)
	public List<Categoria> listar() {
		Categoria c = new Categoria(1, "Informática");
		Categoria c2 = new Categoria(1, "Banho");

		List<Categoria> lista = new ArrayList<>();
		lista.add(c);
		lista.add(c2);

		return lista;
	}

}
