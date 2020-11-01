package br.com.casa.exceptions.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.amazonaws.AmazonClientException;

import br.com.casa.exceptions.AuthorizationException;
import br.com.casa.exceptions.DataIntegridadeException;
import br.com.casa.exceptions.FileException;
import br.com.casa.exceptions.ObjectNotFoundException;
import br.com.casa.exceptions.StandardError;
import br.com.casa.exceptions.ValidationError;
import io.jsonwebtoken.ExpiredJwtException;

/**** INTERCEPTA ERROS ***/
@ControllerAdvice
public class ResourceExceptionHandler {

	/**
	 * Assinatura padrão para receber os erros nas requisições. Vai tratar qualquer
	 * erro do tipo informado no handler
	 ***/
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {

		StandardError standardError = new StandardError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(),
				"Not Found", e.getMessage(), request.getRequestURI().toString());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(standardError);
	}

	@ExceptionHandler(DataIntegridadeException.class)
	public ResponseEntity<StandardError> integridadeDados(DataIntegridadeException e, HttpServletRequest request) {

		StandardError standardError = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),
				"Integridade de Dados", e.getMessage(), request.getRequestURI().toString());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(standardError);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> integridadeDadosBeanValidation(MethodArgumentNotValidException e,
			HttpServletRequest request) {

		ValidationError standardError = new ValidationError(System.currentTimeMillis(),
				HttpStatus.UNPROCESSABLE_ENTITY.value(), "Erro de Validação", e.getMessage(),
				request.getRequestURI().toString());

		for (FieldError x : e.getBindingResult().getFieldErrors()) {
			standardError.setLista(x.getField(), x.getDefaultMessage());
		}

		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(standardError);
	}

	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<StandardError> acessoNegadoPeril(AccessDeniedException e, HttpServletRequest request) {

		StandardError standardError = new StandardError(System.currentTimeMillis(), HttpStatus.UNAUTHORIZED.value(),
				"Acesso negado", e.getMessage(), request.getRequestURI().toString());

		return ResponseEntity.status(HttpStatus.UNAUTHORIZED.value()).body(standardError);
	}

	@ExceptionHandler(AuthorizationException.class)
	public ResponseEntity<StandardError> objectNotFound(AuthorizationException e, HttpServletRequest request) {

		StandardError standardError = new StandardError(System.currentTimeMillis(), HttpStatus.UNAUTHORIZED.value(),
				"Acesso negado", e.getMessage(), request.getRequestURI().toString());

		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(standardError);
	}

	@ExceptionHandler(FileException.class)
	public ResponseEntity<StandardError> file(FileException e, HttpServletRequest request) {

		StandardError standardError = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),
				"Erro no arquivo", e.getMessage(), request.getRequestURI().toString());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(standardError);
	}

	@ExceptionHandler(AmazonClientException.class)
	public ResponseEntity<StandardError> amazonFile(AmazonClientException e, HttpServletRequest request) {
//			HttpStatus httpstatus = HttpStatus.valueOf(e.getErrorCode())
		StandardError standardError = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),
				"Erro na integração com a Amazon", e.getMessage(), request.getRequestURI().toString());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(standardError);
	}

	@ExceptionHandler(ExpiredJwtException.class)
	public ResponseEntity<StandardError> amazonFile(ExpiredJwtException e, HttpServletRequest request) {
//			HttpStatus httpstatus = HttpStatus.valueOf(e.getErrorCode())
		StandardError standardError = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),
				"token expirado", e.getMessage(), request.getRequestURI().toString());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(standardError);
	}

}
