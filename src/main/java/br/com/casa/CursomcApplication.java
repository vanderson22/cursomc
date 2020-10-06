package br.com.casa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.casa.dominio.Categoria;
import br.com.casa.repositories.CategoriaRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository repo;

//	Executar o aplicativo do springboot
	// Ja vem com um tom cat embedded
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

//	Método que permite executar  por linha de comando
	@Override
	public void run(String... args) throws Exception {
		// On start Cria as categirias
		System.out.println("Criadas Categorias");
		Categoria c1 = new Categoria(null, "Informática");
		Categoria c2 = new Categoria(null, "Mesa");
		repo.save(c1);
		repo.save(c2);
	}

}
