package br.com.casa.dominio.enums;

public enum TipoCliente {

	// o jpa na forma padrão gravaria o indice
	PESSOAFISICA(1, "Pessoa Física"), PESSOAJURIDICA(2, "Pessoa Jurídica");

	private int codigo;
	private String descricao;

	private TipoCliente(int codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public int getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	/**
	 * precisa ser estático
	 */
	public static TipoCliente toEnum(Integer cod) {

		if (cod == null)
			return null;

		for (TipoCliente cliente : TipoCliente.values()) {
			if (cod.equals(cliente.getCodigo())) {
				return cliente;
				// Retorna um tipo cliente
			}

		}
		throw new IllegalArgumentException("Código inválido - cod[" + cod + "]");
	}

}
