package br.com.casa.dominio.DTO;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import br.com.casa.dominio.Categoria;


// Atenção a validação não ocorre nas classes de dominio e sim nos DTO.
public class CategoriaDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	@NotEmpty
	@Length(min = 5, max = 100, message = "Não foi possível validar o nome, pois não atende a quantidade de caracteres")
	private String nome;

	public CategoriaDTO() {
	}

	public CategoriaDTO(Integer id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}

	public CategoriaDTO(Categoria cat) {
		super();
		this.id = cat.getId();
		this.nome = cat.getNome();
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

}
