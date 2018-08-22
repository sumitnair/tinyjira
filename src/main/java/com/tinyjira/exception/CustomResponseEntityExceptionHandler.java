package com.tinyjira.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.tinyjira.domain.ErrorDetails;

/**
 * Global Exception handler
 */
@ControllerAdvice
@RestController
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	/* 
	 * Handler method for generic exception.
	 */
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ErrorDetails> handleAllExceptions(Exception ex, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/* 
	 * Handler method for custom exception.
	 * This custom exception is thrown when entities are not found in database.
	 */
	@ExceptionHandler(NotFoundException.class)
	public final ResponseEntity<ErrorDetails> handleUserNotFoundException(NotFoundException ex, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);
	}


	/* 
	 * Handler method for validation errors
	 *
	 */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), "Validation Failed",
				ex.getBindingResult().toString());
		return new ResponseEntity<Object>(errorDetails, HttpStatus.BAD_REQUEST);
	} 
}