package br.com.casa;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.casa.dominio.Categoria;
import br.com.casa.dominio.Cidade;
import br.com.casa.dominio.Estado;
import br.com.casa.dominio.Produto;
import br.com.casa.repositories.CategoriaRepository;
import br.com.casa.repositories.CidadeRepository;
import br.com.casa.repositories.EstadoRepository;
import br.com.casa.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository repo;

	@Autowired
	private ProdutoRepository pRepo;
	@Autowired
	private EstadoRepository estadoRepo;
	@Autowired
	private CidadeRepository cidadeRepo;

//	Executar o aplicativo do springboot
	// Ja vem com um tom cat embedded
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

//	Método que permite executar  por linha de comando
	@Override
	public void run(String... args) throws Exception {
		// On start Cria as categirias
		// Instanciação do Projeto -> Isso é um teste funcional
		System.out.println("Criadas Categorias");
		Categoria c1 = new Categoria(null, "Informática");
		Categoria c2 = new Categoria(null, "Escritório");
		Produto p1 = new Produto(null, "Computador", 2000.0);
		Produto p2 = new Produto(null, "Impressora", 300.0);
		Produto p3 = new Produto(null, "Mouse", 80.0);

		c1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		c2.getProdutos().addAll(Arrays.asList(p2));

		p1.getCategorias().addAll(Arrays.asList(c1));
		p2.getCategorias().addAll(Arrays.asList(c1, c2));
		p3.getCategorias().addAll(Arrays.asList(c1));

		repo.saveAll(Arrays.asList(c1, c2));
		pRepo.saveAll(Arrays.asList(p1, p2, p3));

		Estado e1 = new Estado(null, "Minas Gerais");
		Estado e2 = new Estado(null, "São Paulo");

		Cidade cid1 = new Cidade(null, "Uberlândia", e1);
		Cidade cid2 = new Cidade(null, "Santos", e2);
		Cidade cid3 = new Cidade(null, "Campinas", e2);

		e1.getCidades().addAll(Arrays.asList(cid1));
		e2.getCidades().addAll(Arrays.asList(cid2, cid3));

		estadoRepo.saveAll(Arrays.asList(e1, e2));
		cidadeRepo.saveAll(Arrays.asList(cid1, cid2, cid3));

		System.out.println("Finalizou a instanciação");
//		repo.save(c1);
//		repo.save(c2);
	}

}
