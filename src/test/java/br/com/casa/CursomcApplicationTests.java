package br.com.casa;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.casa.resources.CategoriaResource;

@SpringBootTest
class CursomcApplicationTests {

	@Autowired
	private CategoriaResource controller;

	@Test
	void contextLoads() {

		assertThat(controller).isNotNull();
	}

	@Test
	void dominio() {

//		Optional<Categoria> object = controller.buscar(1);
//
//		Assert.notNull(object, "A Classe não pode ser nula");
	}

}
