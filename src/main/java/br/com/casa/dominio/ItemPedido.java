
package br.com.casa.dominio;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ItemPedido implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Chave composta
	@JsonIgnore
	@EmbeddedId
	private ProdutoPedidoPK pk = new ProdutoPedidoPK();

	private Double desconto;
	private Integer quantidade;
	private Double preco;

	public ItemPedido() {
	}

	public ItemPedido(Pedido pedido, Produto produto, Double desconto, Integer quantidade, Double preco) {
		super();
		pk.setPedido(pedido);
		pk.setProduto(produto);
		this.desconto = desconto;
		this.quantidade = quantidade;
		this.preco = preco;
	}

	// PULO DO GATO, observe os dois métodos acessando pedido e produto, gerando uma
	// referencia circular.
	@JsonIgnore
	public Pedido getPedido() {

		return pk.getPedido();
	}

	public Double getSubtotal() {
		Double subtotal = 0.0;
		subtotal = quantidade * (preco - desconto);

		return subtotal;
	}

//	@JsonIgnore
	public Produto getProduto() {

		return pk.getProduto();
	}

	public ProdutoPedidoPK getPk() {
		return pk;
	}

	public void setPk(ProdutoPedidoPK pk) {
		this.pk = pk;
	}

	public Double getDesconto() {
		return desconto;
	}

	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pk == null) ? 0 : pk.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemPedido other = (ItemPedido) obj;
		if (pk == null) {
			if (other.pk != null)
				return false;
		} else if (!pk.equals(other.pk))
			return false;
		return true;
	}

}
