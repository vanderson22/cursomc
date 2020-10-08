package br.com.casa.dominio;

import br.com.casa.dominio.enums.EstadoPagamento;

public class PagamentoCartao extends Pagamento {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer parcelas;

	public PagamentoCartao() {
		super();
	}

	public PagamentoCartao(Integer id, EstadoPagamento estado, Pedido pedido, Integer parcelas) {
		super(id, estado, pedido);
		this.setParcelas(parcelas);
	}

	public Integer getParcelas() {
		return parcelas;
	}

	public void setParcelas(Integer parcelas) {
		this.parcelas = parcelas;
	}

	@Override
	public String toString() {
		return "PagamentoCartao [parcelas=" + parcelas + "]";
	}
	
	

}
