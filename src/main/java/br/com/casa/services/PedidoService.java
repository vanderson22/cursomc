package br.com.casa.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.casa.dominio.ItemPedido;
import br.com.casa.dominio.PagamentoBoleto;
import br.com.casa.dominio.Pedido;
import br.com.casa.dominio.enums.EstadoPagamento;
import br.com.casa.exceptions.ObjectNotFoundException;
import br.com.casa.repositories.PedidoRepository;

@Service
public class PedidoService {

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

	public Pedido buscar(Integer id) throws ObjectNotFoundException {

		Optional<Pedido> optional = repo.findById(id);

		return optional
				.orElseThrow(() -> new ObjectNotFoundException("Pedido não encontrado  - identificador :[" + id + "]"));

	}

	public Pedido criar(Pedido pedido) {

		// garantia de novo pedido
		pedido.setId(null);
		pedido.setInstante(new Date());
		
		pedido.getEnderecoEntrega();

		pedido.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		pedido.getPagamento().setPedido(pedido);

		if (pedido.getPagamento() instanceof PagamentoBoleto) {
			PagamentoBoleto boleto = (PagamentoBoleto) pedido.getPagamento();
			boletoService.preencherPagamentoBoleto(boleto, new Date());

		}
//
//		if (pedido.getPagamento() instanceof PagamentoCartao) {
//			PagamentoCartao pc = (PagamentoCartao) pedido.getPagamento();
//		}
		// isso não é recomendável, crie sempre uma nova variável
		pedido = repo.save(pedido);
		pagamentoService.criar(pedido.getPagamento());
		for (ItemPedido i : pedido.getItens()) {
			i.setDesconto(0.0);
			// pegando preços.
			i.setPreco(produtoRepo.buscar(i.getProduto().getId()).getPreco());

			// após criar o pedido realizar a associação do id
			i.setPedido(pedido);
		}

		// salva os itens do pedido
		itemService.criar(pedido.getItens());

		return pedido;
	}

}
