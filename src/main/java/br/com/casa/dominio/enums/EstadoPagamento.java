package br.com.casa.dominio.enums;

public enum EstadoPagamento {

	PENDENTE(1, "Pendente"), QUITADO(2, "Quitado"), CANCELADO(3, "Cancelado");

	private Integer codigo;
	private String estado;

	private EstadoPagamento(Integer codigo, String estado) {
		this.codigo = codigo;
		this.estado = estado;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public static EstadoPagamento toEnum(Integer codigo) {

		if (codigo == null)
			return null;
		for (EstadoPagamento estadoPagamento : EstadoPagamento.values()) {
			if (codigo.equals(estadoPagamento.getCodigo()))
				return estadoPagamento;

		}
		throw new IllegalArgumentException("Código inválido - cod[" + codigo + "]");
	}

	public String getEstado() {
		return estado;
	}

}
