package br.com.casa;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.casa.dominio.Categoria;
import br.com.casa.dominio.Cidade;
import br.com.casa.dominio.Cliente;
import br.com.casa.dominio.Endereco;
import br.com.casa.dominio.Estado;
import br.com.casa.dominio.Pagamento;
import br.com.casa.dominio.PagamentoBoleto;
import br.com.casa.dominio.PagamentoCartao;
import br.com.casa.dominio.Pedido;
import br.com.casa.dominio.Produto;
import br.com.casa.dominio.enums.EstadoPagamento;
import br.com.casa.dominio.enums.TipoCliente;
import br.com.casa.repositories.CategoriaRepository;
import br.com.casa.repositories.CidadeRepository;
import br.com.casa.repositories.ClienteRepository;
import br.com.casa.repositories.EnderecoRepository;
import br.com.casa.repositories.EstadoRepository;
import br.com.casa.repositories.PagamentoRepository;
import br.com.casa.repositories.PedidoRepository;
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

	@Autowired
	private ClienteRepository cliRepo;
	@Autowired
	private EnderecoRepository endRepo;

	@Autowired
	private PedidoRepository pedidoRepo;

	@Autowired
	private PagamentoRepository pagamentoRepo;

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

		Cliente cli1 = new Cliente(null, "Maria silva", "maria@mail.com", "003.153.160-00", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("222-3333", "323-4544"));
		Cliente cli2 = new Cliente(null, "Joana silva", "joana.silva@mail.com", "003.153.160-00",
				TipoCliente.PESSOAFISICA);

		cli2.getTelefones().addAll(Arrays.asList("222-3333", "323-4544"));

		Endereco end1 = new Endereco(null, "Rua Flores", "3001", "apto 303", "Jardim", "38229834", cli1, cid1);

		Endereco end2 = new Endereco(null, "Aveninda Mattos", "31", "Casa Verde", "Jargim", "38777012", cli1, cid2);

		cli1.getEnderecos().addAll(Arrays.asList(end1, end2));

		// SALVAR SEMPRE OS INDEPENDENTES PRIMEIRO EX: CLIENTE
		cliRepo.saveAll(Arrays.asList(cli1, cli2));
		endRepo.saveAll(Arrays.asList(end1, end2));

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Pedido ped1 = new Pedido(null, sdf.parse("10/02/2020 10:30"), cli1, end1);
		Pedido ped2 = new Pedido(null, sdf.parse("13/03/2020 21:10"), cli1, end2);

		Pagamento pg1 = new PagamentoBoleto(null, EstadoPagamento.QUITADO, ped1, sdf.parse("13/03/2020 21:10"),
				new Date());
		ped1.setPagamento(pg1);

		Pagamento pg2 = new PagamentoCartao(null, EstadoPagamento.PENDENTE, ped2, null);
		ped2.setPagamento(pg2);

		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));

		pedidoRepo.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepo.saveAll(Arrays.asList(pg1, pg2));

		System.out.println("Finalizou a instanciação");
//		repo.save(c1);
//		repo.save(c2);
	}

}
