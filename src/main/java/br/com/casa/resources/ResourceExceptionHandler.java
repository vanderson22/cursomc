package br.com.casa.resources;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.casa.exceptions.DataIntegridadeException;
import br.com.casa.exceptions.ObjectNotFoundException;

/**** INTERCEPTA ERROS ***/
@ControllerAdvice
public class ResourceExceptionHandler {

	/**
	 * Assinatura padrão para receber os erros nas requisições.
	 * Vai tratar qualquer erro do tipo informado no handler
	 ***/
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {

		StandardError standardError = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(),
				System.currentTimeMillis());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(standardError);
	}
	
	@ExceptionHandler(DataIntegridadeException.class)
	public ResponseEntity<StandardError> integridadeDados(DataIntegridadeException e, HttpServletRequest request) {

		StandardError standardError = new StandardError(HttpStatus.BAD_REQUEST.value(), e.getMessage(),
				System.currentTimeMillis());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(standardError);
	}

}
