package br.com.casa.dominio.DTO;

import java.io.Serializable;

/**
 *    Credenciais que serão recuperadas do /login.
 *    O nome das propriedades é o mesmo nome que será recebido no e-mail
 * 
 * **/
public class CredenciaisDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String email;
	private String senha;
	
	
	
	public CredenciaisDTO() {
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getSenha() {
		return senha;
	}



	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	

}
