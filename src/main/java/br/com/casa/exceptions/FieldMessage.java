package br.com.casa.exceptions;

/***
 * Apenas para modelar um retorno HTTP
 * 
 */
public class FieldMessage {

	private String campo;
	private String mensagem;

	public FieldMessage(String fieldName, String msg) {
		this.campo = fieldName;
		this.mensagem = msg;
	}

	public String getCampo() {
		return campo;
	}

	public void setCampo(String fieldName) {
		this.campo = fieldName;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String msg) {
		this.mensagem = msg;
	}

}
