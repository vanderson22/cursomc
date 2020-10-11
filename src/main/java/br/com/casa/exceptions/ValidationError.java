package br.com.casa.exceptions;

import java.util.ArrayList;
import java.util.List;

/***
 * Apenas para modelar um retorno HTTP
 * 
 */
public class ValidationError extends StandardError {

	private List<FieldMessage> lista = new ArrayList<>();

	public ValidationError(Integer status, String msg, Long timestamp) {
		super(status, msg, timestamp);
	}

	public List<FieldMessage> getErrors() {
		return lista;
	}

	public void setLista(String nome, String msg) {
		lista.add(new FieldMessage(nome, msg));
	}

}
