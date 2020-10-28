package br.com.casa.exceptions;

/***
 * Apenas para modelar um retorno HTTP
 * 
 */
public class StandardError {

	private Integer status;
	private String mensagem;
	private Long timestamp;

	public StandardError(Integer status, String msg, Long timestamp) {
		super();
		this.status = status;
		this.mensagem = msg;
		this.timestamp = timestamp;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String msg) {
		this.mensagem = msg;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

}
