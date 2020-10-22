package br.com.casa.dominio.enums;


/**
 *  Perfis de Cliente, iniciar sempre com ROLE, pois o framework exige
 * 
 * **/
public enum Perfil {

	ADMIN(1, "ROLE_ADMIN"),
	CLIENTE(2, "ROLE_CLIENTE"), 
	OPERACOES(3, "ROLE_OPERACOES");

	private Integer codigo;
	private String estado;

	private Perfil(Integer codigo, String estado) {
		this.codigo = codigo;
		this.estado = estado;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public static Perfil toEnum(Integer codigo) {

		if (codigo == null)
			return null;
		for (Perfil estadoPagamento : Perfil.values()) {
			if (codigo.equals(estadoPagamento.getCodigo()))
				return estadoPagamento;

		}
		throw new IllegalArgumentException("Código inválido - cod[" + codigo + "]");
	}

	public String getEstado() {
		return estado;
	}

}
