package br.com.casa.exceptions;

public class AuthenticationHomeException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AuthenticationHomeException(String msg) {
		super(msg);

	}

	public AuthenticationHomeException(String msg, Throwable t) {
		super(msg, t);

		
	}
}
