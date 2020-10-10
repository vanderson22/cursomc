package br.com.casa.exceptions;

public class DataIntegridadeException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DataIntegridadeException(String msg) {
		super(msg);

	}

	public DataIntegridadeException(String msg, Throwable t) {
		super(msg, t);

		
	}
}
