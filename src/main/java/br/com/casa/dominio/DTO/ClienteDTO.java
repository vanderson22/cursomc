package br.com.casa.dominio.DTO;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import br.com.casa.dominio.Cliente;

public class ClienteDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// CPF não pode mudar e não entra

	private Integer id;

	@NotEmpty(message = "Não pode ser vazio, o nome do Cliente")
	@Length(min = 3, max = 80, message = "O nome precisa ter um tamanho mínimo e máximo")
	private String nome;

	@NotEmpty(message = "Não pode ser vazio, EMAIL")
	@Length(min = 3, max = 80, message = "O EMAIL precisa ter um tamanho mínimo e máximo")
	private String email;

	public ClienteDTO(Cliente obj) {
		this.nome = obj.getNome();
		this.email = obj.getEmail();
		this.id = obj.getId();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ClienteDTO() {
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
