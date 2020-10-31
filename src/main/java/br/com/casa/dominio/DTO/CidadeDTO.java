package br.com.casa.dominio.DTO;

import br.com.casa.dominio.Estado;

public class CidadeDTO {

	private Integer id;
	private String nome;

	private Estado estado;

	public CidadeDTO() {
	}

	public CidadeDTO(Integer id, String nome, Estado estado) {
		super();
		this.id = id;
		this.nome = nome;
		this.estado = estado;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

}
