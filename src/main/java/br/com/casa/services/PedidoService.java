package br.com.casa.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.casa.dominio.ItemPedido;
import br.com.casa.dominio.PagamentoBoleto;
import br.com.casa.dominio.Pedido;
import br.com.casa.dominio.Produto;
import br.com.casa.dominio.enums.EstadoPagamento;
import br.com.casa.exceptions.ObjectNotFoundException;
import br.com.casa.repositories.PedidoRepository;

@Service
public class PedidoService {

	private static final Logger log = LoggerFactory.getLogger(PedidoService.class);

	@Autowired
	private PedidoRepository repo;
	@Autowired
	private BoletoService boletoService;

	@Autowired
	private PagamentoService pagamentoService;
	@Autowired
	private ProdutoService produtoRepo;
	@Autowired
	private ItemPedidoService itemService;
	@Autowired
	private ClienteService clienteService;

	@Autowired
	private EnderecoService endService;

	@Autowired
	private EmailService ems;

	@Value("${email.enabled}")
	private String ligado;

	public Pedido buscar(Integer id) throws ObjectNotFoundException {

		Optional<Pedido> optional = repo.findById(id);

		return optional
				.orElseThrow(() -> new ObjectNotFoundException("Pedido não encontrado  - identificador :[" + id + "]"));

	}

	@Transactional
	public Pedido criar(Pedido pedido) {

		log.info("Criando novo pedido ...");
		// garantia de novo pedido
		pedido.setId(null);
		pedido.setInstante(new Date());

		pedido.setEnderecoEntrega(endService.buscarPorId(pedido.getEnderecoEntrega().getId()));
		pedido.setCliente(clienteService.buscar(pedido.getCliente().getId()));

		pedido.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		pedido.getPagamento().setPedido(pedido);

		if (pedido.getPagamento() instanceof PagamentoBoleto) {
			PagamentoBoleto boleto = (PagamentoBoleto) pedido.getPagamento();
			boletoService.preencherPagamentoBoleto(boleto, new Date());

		}
		// isso não é recomendável, crie sempre uma nova variável
		pedido = repo.save(pedido);
		pagamentoService.criar(pedido.getPagamento());
		for (ItemPedido i : pedido.getItens()) {

			Produto produto = produtoRepo.buscar(i.getProduto().getId());
			i.setDesconto(0.0);
			// pegando preços.
			i.setProduto(produto);
			i.setPreco(produto.getPreco());
			// após criar o pedido realizar a associação do id
			i.setPedido(pedido);
		}
		log.info("criado novo pedido - id [" + pedido.getId() + "]");

		// salva os itens do pedido
		itemService.criar(pedido.getItens());

		if (ligado.equals("true")) {
			ems.sendConfirmationMail(pedido);
		}
		return pedido;
	}

	public List<Pedido> buscar() {

		List<Pedido> optional = repo.findAll();

		return optional;
	}

}
