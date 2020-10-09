package br.com.casa.dominio;

import java.util.Date;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.casa.dominio.enums.EstadoPagamento;

@Entity

public class PagamentoBoleto extends Pagamento {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataVencimento;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataPagamento;

	public PagamentoBoleto() {
		super();
	}

	public PagamentoBoleto(Integer id, EstadoPagamento estado, Pedido pedido, Date dataPagamento, Date dataVencimento) {
		super(id, estado, pedido);

		this.dataPagamento = dataPagamento;
		this.dataVencimento = dataVencimento;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

}
