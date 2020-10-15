package br.com.casa.config;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.casa.dominio.Categoria;
import br.com.casa.dominio.Cidade;
import br.com.casa.dominio.Cliente;
import br.com.casa.dominio.Endereco;
import br.com.casa.dominio.Estado;
import br.com.casa.dominio.ItemPedido;
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
import br.com.casa.repositories.ItemRepository;
import br.com.casa.repositories.PagamentoRepository;
import br.com.casa.repositories.PedidoRepository;
import br.com.casa.repositories.ProdutoRepository;

/**
 * Vai realizar a configuração com o profile test
 */
@Configuration
@Profile(value = "test")
public class TestConfiguration {
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

	@Autowired
	private ItemRepository itemRepo;

	@Bean
	public boolean instantiateTSTConfiguration() throws ParseException {
		// On start Cria as categirias
		// Instanciação do Projeto -> Isso é um teste funcional
		System.out.println("Criadas Categorias");
		Categoria c1 = new Categoria(null, "Informática");
		Categoria c2 = new Categoria(null, "Escritório");
		Categoria c3 = new Categoria(null, "Farmácia");
		Categoria c4 = new Categoria(null, "Vestuário");
		Categoria c5 = new Categoria(null, "Armarinho");
		Categoria c6 = new Categoria(null, "GAME");
		Categoria c7 = new Categoria(null, "TV");
		Categoria c8 = new Categoria(null, "Cozinha");
		Categoria c9 = new Categoria(null, "Construção");

		Produto p1 = new Produto(null, "Computador", 2000.0);
		Produto p2 = new Produto(null, "Impressora", 300.0);
		Produto p3 = new Produto(null, "Mouse", 80.0);
		Produto p4 = new Produto(null, "Teclado", 80.0);
		Produto p5 = new Produto(null, "Panela", 180.0);
		Produto p6 = new Produto(null, "Toalha", 80.0);
		Produto p7 = new Produto(null, "Mesa", 80.0);
		Produto p8 = new Produto(null, "Celular", 80.0);
		Produto p9 = new Produto(null, "Lapis", 80.0);
//		Produto p10 = new Produto(null, "Monitor", 80.0);
		Produto p11 = new Produto(null, "Estante", 80.0);

		c1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		c2.getProdutos().addAll(Arrays.asList(p2));
		c4.getProdutos().addAll(Arrays.asList(p6, p11));
		c8.getProdutos().addAll(Arrays.asList(p3));
		c7.getProdutos().addAll(Arrays.asList(p7));
		c5.getProdutos().addAll(Arrays.asList(p5));
		c4.getProdutos().addAll(Arrays.asList(p4));

		p1.getCategorias().addAll(Arrays.asList(c1));
		p2.getCategorias().addAll(Arrays.asList(c1, c2));
		p3.getCategorias().addAll(Arrays.asList(c1, c8));
		p6.getCategorias().addAll(Arrays.asList(c6));
		p5.getCategorias().addAll(Arrays.asList(c5));
		p4.getCategorias().addAll(Arrays.asList(c4));
		p7.getCategorias().addAll(Arrays.asList(c7));

		repo.saveAll(Arrays.asList(c1, c2, c3, c4, c5, c6, c7, c8, c9));
		pRepo.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9));

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

		Endereco end2 = new Endereco(null, "Aveninda Mattos", "31", "Casa Verde", "Jardim", "38777012", cli1, cid2);

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

		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2999.99);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 1, 200.99);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 0.00, 1, 1500.99);

		// associar pedido com item
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));

		itemRepo.saveAll(Arrays.asList(ip1, ip2, ip3));

		System.out.println("Finalizou a instanciação");
//		repo.save(c1);
//		repo.save(c2);
		return true;
	}

}
