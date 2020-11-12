package br.com.casa.config.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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
import br.com.casa.dominio.enums.Perfil;
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
import br.com.casa.services.PedidoService;

/**
 * Vai realizar a configuração com o profile test
 */

@Service
public class DevInstantiateDB {
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

	@Autowired
	private BCryptPasswordEncoder pe;

	private static final Logger log = LoggerFactory.getLogger(PedidoService.class);

	public boolean instantiateDEVConfiguration() throws ParseException {
		log.info("Instanciando banco de dados em Desenvolvimento");
		// On start Cria as categirias
		// Instanciação do Projeto -> Isso é um teste funcional
		log.info("Criadas Categorias");
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria c2 = new Categoria(null, "Escritório");
		Categoria c3 = new Categoria(null, "Farmácia");
		Categoria c4 = new Categoria(null, "Vestuário");
		Categoria c5 = new Categoria(null, "Armarinho");
		Categoria c6 = new Categoria(null, "GAME");
		Categoria c7 = new Categoria(null, "TV");
//		Categoria c8 = new Categoria(null, "Cozinha");
//		Categoria c9 = new Categoria(null, "Construção");

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
		Produto p12 = new Produto(null, "Produto 12", 10.00);
		Produto p13 = new Produto(null, "Produto 13", 10.00);
		Produto p14 = new Produto(null, "Produto 14", 10.00);
		Produto p15 = new Produto(null, "Produto 15", 10.00);
		Produto p16 = new Produto(null, "Produto 16", 10.00);
		Produto p17 = new Produto(null, "Produto 17", 10.00);
		Produto p18 = new Produto(null, "Produto 18", 10.00);
		Produto p19 = new Produto(null, "Produto 19", 10.00);
		Produto p20 = new Produto(null, "Produto 20", 10.00);
		Produto p21 = new Produto(null, "Produto 21", 10.00);
		Produto p22 = new Produto(null, "Produto 22", 10.00);
		Produto p23 = new Produto(null, "Produto 23", 10.00);
		Produto p24 = new Produto(null, "Produto 24", 10.00);
		Produto p25 = new Produto(null, "Produto 25", 10.00);
		Produto p26 = new Produto(null, "Produto 26", 10.00);
		Produto p27 = new Produto(null, "Produto 27", 10.00);
		Produto p28 = new Produto(null, "Produto 28", 10.00);
		Produto p29 = new Produto(null, "Produto 29", 10.00);
		Produto p30 = new Produto(null, "Produto 30", 10.00);
		Produto p31 = new Produto(null, "Produto 31", 10.00);
		Produto p32 = new Produto(null, "Produto 32", 10.00);
		Produto p33 = new Produto(null, "Produto 33", 10.00);
		Produto p34 = new Produto(null, "Produto 34", 10.00);
		Produto p35 = new Produto(null, "Produto 35", 10.00);
		Produto p36 = new Produto(null, "Produto 36", 10.00);
		Produto p37 = new Produto(null, "Produto 37", 10.00);
		Produto p38 = new Produto(null, "Produto 38", 10.00);
		Produto p39 = new Produto(null, "Produto 39", 10.00);
		Produto p40 = new Produto(null, "Produto 40", 10.00);
		Produto p41 = new Produto(null, "Produto 41", 10.00);
		Produto p42 = new Produto(null, "Produto 42", 10.00);
		Produto p43 = new Produto(null, "Produto 43", 10.00);
		Produto p44 = new Produto(null, "Produto 44", 10.00);
		Produto p45 = new Produto(null, "Produto 45", 10.00);
		Produto p46 = new Produto(null, "Produto 46", 10.00);
		Produto p47 = new Produto(null, "Produto 47", 10.00);
		Produto p48 = new Produto(null, "Produto 48", 10.00);
		Produto p49 = new Produto(null, "Produto 49", 10.00);
		Produto p50 = new Produto(null, "Produto 50", 10.00);
		cat1.getProdutos()
				.addAll(Arrays.asList(p12, p13, p14, p15, p16, p17, p18, p19, p20, p21, p22, p23, p24, p25, p26, p27,
						p28, p29, p30, p31, p32, p34, p35, p36, p37, p38, p39, p40, p41, p42, p43, p44, p45, p46, p47,
						p48, p49, p50));
		p12.getCategorias().add(cat1);
		p13.getCategorias().add(cat1);
		p14.getCategorias().add(cat1);
		p15.getCategorias().add(cat1);
		p16.getCategorias().add(cat1);
		p17.getCategorias().add(cat1);
		p18.getCategorias().add(cat1);
		p19.getCategorias().add(cat1);
		p20.getCategorias().add(cat1);
		p21.getCategorias().add(cat1);
		p22.getCategorias().add(cat1);
		p23.getCategorias().add(cat1);
		p24.getCategorias().add(cat1);
		p25.getCategorias().add(cat1);
		p26.getCategorias().add(cat1);
		p27.getCategorias().add(cat1);
		p28.getCategorias().add(cat1);
		p29.getCategorias().add(cat1);
		p30.getCategorias().add(cat1);
		p31.getCategorias().add(cat1);
		p32.getCategorias().add(cat1);
		p33.getCategorias().add(cat1);
		p34.getCategorias().add(cat1);
		p35.getCategorias().add(cat1);
		p36.getCategorias().add(cat1);
		p37.getCategorias().add(cat1);
		p38.getCategorias().add(cat1);
		p39.getCategorias().add(cat1);
		p40.getCategorias().add(cat1);
		p41.getCategorias().add(cat1);
		p42.getCategorias().add(cat1);
		p43.getCategorias().add(cat1);
		p44.getCategorias().add(cat1);
		p45.getCategorias().add(cat1);
		p46.getCategorias().add(cat1);
		p47.getCategorias().add(cat1);
		p48.getCategorias().add(cat1);
		p49.getCategorias().add(cat1);
		p50.getCategorias().add(cat1);
//		2) Inserir os produtos:

		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		c2.getProdutos().addAll(Arrays.asList(p2));
		c4.getProdutos().addAll(Arrays.asList(p6, p11));
//		c8.getProdutos().addAll(Arrays.asList(p3));
		c7.getProdutos().addAll(Arrays.asList(p7));
		c5.getProdutos().addAll(Arrays.asList(p5));
		c4.getProdutos().addAll(Arrays.asList(p4));

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, c2));
//		p3.getCategorias().addAll(Arrays.asList(cat1, c8));
		p6.getCategorias().addAll(Arrays.asList(c6));
		p5.getCategorias().addAll(Arrays.asList(c5));
		p4.getCategorias().addAll(Arrays.asList(c4));
		p7.getCategorias().addAll(Arrays.asList(c7));

		repo.saveAll(Arrays.asList(cat1, c2, c3, c4, c5, c6, c7));

		pRepo.saveAll(Arrays.asList(p12, p13, p14, p15, p16, p17, p18, p19, p20, p21, p22, p23, p24, p25, p26, p27, p28,
				p29, p30, p31, p32, p34, p35, p36, p37, p38, p39, p40, p41, p42, p43, p44, p45, p46, p47, p48, p49,
				p50));
		pRepo.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9));

		Estado e1 = new Estado(null, "Minas Gerais");
		Estado e2 = new Estado(null, "São Paulo");

		Cidade cid1 = new Cidade(null, "Uberlândia", e1);
		Cidade cid1_2 = new Cidade(null, "Jardim Mineiro", e1);
		Cidade cid1_3 = new Cidade(null, "Belo Horizonte", e1);
		Cidade cid2 = new Cidade(null, "Santos", e2);
		Cidade cid3 = new Cidade(null, "Campinas", e2);

		e1.getCidades().addAll(Arrays.asList(cid1, cid1_2, cid1_3));
		e2.getCidades().addAll(Arrays.asList(cid2, cid3));

		estadoRepo.saveAll(Arrays.asList(e1, e2));
		cidadeRepo.saveAll(Arrays.asList(cid1, cid2, cid3));

		Cliente cli1 = new Cliente(null, "Vanderson nogueira", "vanderson.01@hotmail.com", "003.153.160-00",
				TipoCliente.PESSOAFISICA).senha(pe.encode("12345"));

		cli1.getTelefones().addAll(Arrays.asList("222-3333", "323-4544"));
		Cliente cli2 = new Cliente(null, "Joana silva", "joana.silva@mail.com", "003.153.160-55",
				TipoCliente.PESSOAFISICA).senha(pe.encode("54321"));
//		 Também é um admin
		cli2.setPerfis(Perfil.ADMIN);
		
		Cliente cli3 = new Cliente(null, "system User", "sys@mail.com", "003.153.160-11",
				TipoCliente.PESSOAFISICA).senha(pe.encode("123"));
//		 Também é um admin
		cli3.setPerfis(Perfil.ADMIN);

		cli2.getTelefones().addAll(Arrays.asList("222-3333", "323-4544"));

		Endereco end1 = new Endereco(null, "Rua Flores", "3001", "apto 303", "Jardim", "38229834", cli1, cid1);

		Endereco end2 = new Endereco(null, "Aveninda Mattos", "31", "Casa Verde", "Jardim", "38777012", cli1, cid2);

		cli1.getEnderecos().addAll(Arrays.asList(end1, end2));

		// SALVAR SEMPRE OS INDEPENDENTES PRIMEIRO EX: CLIENTE
		cliRepo.saveAll(Arrays.asList(cli1, cli2, cli3));
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

		log.info("Finalizou a instanciação");
//		repo.save(c1);
//		repo.save(c2);
		return true;
	}

}
