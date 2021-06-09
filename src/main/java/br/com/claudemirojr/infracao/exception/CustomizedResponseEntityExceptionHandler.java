package br.com.claudemirojr.infracao.exception;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@Autowired
	private MessageSource messageSource;
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public final ResponseEntity<ExceptionResponse> handleBadRequestExceptions(Exception ex, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
	}

	// Campos não passaram em @Valid
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ArrayList<ExceptionResponse> erros = new ArrayList<ExceptionResponse>();

		ex.getBindingResult().getAllErrors().forEach(error -> {
			String campo = ((FieldError) error).getField();
			String mensagem = messageSource.getMessage(error, LocaleContextHolder.getLocale());

			erros.add(new ExceptionResponse(new Date(), campo, mensagem));
		});

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erros);
	}
	
	// Validação do dado para a camada de negócio
	@ExceptionHandler(ResourceServiceValidationException.class)
	public final ResponseEntity<Object> handleResourceServiceValidationException(Exception ex, WebRequest request) {
		ArrayList<ExceptionResponse> erros = new ArrayList<ExceptionResponse>();

		String[] campos = ex.getMessage().split(";");
		
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), campos[0], campos[1] );
		
		erros.add(exceptionResponse);
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erros);
	}
	
}
