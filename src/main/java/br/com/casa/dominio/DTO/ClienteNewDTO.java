package br.com.casa.dominio.DTO;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import br.com.casa.dominio.annotations.validation.ClienteInsert;

/**
 * Utilizado para gravar com um novo cliente com Endereço e cidade
 * 
 * 
 ***/
@ClienteInsert(message = "Ocorreu um erro de validação")
public class ClienteNewDTO {

	@NotEmpty(message = "Preenchimento obrigatório")
	@Length(min = 5, max = 100, message = "Não foi possível validar o nome, pois não atende a quantidade de caracteres")
	private String nome;

	@NotEmpty(message = "Preenchimento obrigatório")
	private String email;

//	@NotEmpty(message = "Preenchimento obrigatório")
//	@CPF
//	@CNPJ
	private String cpfCnpj;
	
	@NotEmpty(message = "A senha não pode ser vazia")
	private String senha;

	private Integer tipo;
	private String logradouro;
	private String numero;
	private String complemento;
	private String bairro;
	private String cep;
	private Integer cidadeId;

	private String telefone1;
	private String telefone2;
	private String telefone3;

	public ClienteNewDTO() {

	}

	public ClienteNewDTO(String nome, String email, String cpfCnpj, Integer tipo, String logradouro, String numero,
			String complemento, String bairro, String cep, Integer cidadeId, String telefone1, String telefone2,
			String telefone3) {
		super();
		this.nome = nome;
		this.email = email;
		this.cpfCnpj = cpfCnpj;
		this.tipo = tipo;
		this.logradouro = logradouro;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cep = cep;
		this.cidadeId = cidadeId;
		this.telefone1 = telefone1;
		this.telefone2 = telefone2;
		this.telefone3 = telefone3;
	}
	
	
	public ClienteNewDTO senha() {
		   this.setSenha(senha);
		return this;
	}
	
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Integer getCidadeId() {
		return cidadeId;
	}

	public void setCidadeId(Integer cidadeId) {
		this.cidadeId = cidadeId;
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

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getTelefone1() {
		return telefone1;
	}

	public void setTelefone1(String telefone1) {
		this.telefone1 = telefone1;
	}

	public String getTelefone2() {
		return telefone2;
	}

	public void setTelefone2(String telefone2) {
		this.telefone2 = telefone2;
	}

	public String getTelefone3() {
		return telefone3;
	}

	public void setTelefone3(String telefone3) {
		this.telefone3 = telefone3;
	}

}
