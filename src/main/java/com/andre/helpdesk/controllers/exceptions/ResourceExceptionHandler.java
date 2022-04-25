package com.andre.helpdesk.controllers.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.andre.helpdesk.services.exceptions.DataIntegrityViolationException;
import com.andre.helpdesk.services.exceptions.ObjectNotFoundException;
import com.andre.helpdesk.services.exceptions.StringIndexOutOfBoundsException;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> resourceNotFound(ObjectNotFoundException ex, HttpServletRequest request){
		
		String error = "Object not found!";
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError(Instant.now(), status.value(), error, ex.getMessage(), request.getRequestURI());
		
		return ResponseEntity.ok().body(err);
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<StandardError> dataIntegrityViolationException(DataIntegrityViolationException ex, HttpServletRequest request){
		
		String error = "Violação de dados";
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError(Instant.now(), status.value(), error, ex.getMessage(), request.getRequestURI());
		
		return ResponseEntity.ok().body(err);
	}
	
	@ExceptionHandler(StringIndexOutOfBoundsException.class)
	public ResponseEntity<StandardError> stringIndexOutOfBoundsException(StringIndexOutOfBoundsException ex, HttpServletRequest request){
		
		String error = "String com tamanho errado.";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError err = new StandardError(Instant.now(), status.value(), error, ex.getMessage(), request.getRequestURI());
		
		return ResponseEntity.ok().body(err);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> validationErrors(MethodArgumentNotValidException ex, HttpServletRequest request){
		
		String error = "Erro na validação dos campos.";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		ValidationError errors = new ValidationError(Instant.now(), status.value(), "Validation error", error, request.getRequestURI());
		
		for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
			errors.addError(fieldError.getField(), fieldError.getDefaultMessage());
		}
		
		return ResponseEntity.ok().body(errors);
	}
}
