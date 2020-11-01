package br.com.casa.exceptions;

import java.util.ArrayList;
import java.util.List;

/***
 * Apenas para modelar um retorno HTTP
 * 
 */
public class ValidationError extends StandardError {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<FieldMessage> lista = new ArrayList<>();

	public ValidationError(Long timestamp, Integer status, String error, String message, String path) {
		super(timestamp, status, error, message, path);
	}

	public List<FieldMessage> getErrors() {
		return lista;
	}

	public void setLista(String nome, String msg) {
		lista.add(new FieldMessage(nome, msg));
	}

}
