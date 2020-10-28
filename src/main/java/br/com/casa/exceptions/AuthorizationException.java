package br.com.casa.exceptions;

public class AuthorizationException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AuthorizationException(String msg) {
		super(msg);

	}

	public AuthorizationException(String msg, Throwable t) {
		super(msg, t);

		
	}
}
