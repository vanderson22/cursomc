package br.com.casa.exceptions;

/***
 * Apenas para modelar um retorno HTTP
 * 
 */
public class StandardError {

	private Integer status;
	private String message;
	private Long timestamp;

	public StandardError(Integer status, String msg, Long timestamp) {
		super();
		this.status = status;
		this.message = msg;
		this.timestamp = timestamp;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String msg) {
		this.message = msg;
	}

}
