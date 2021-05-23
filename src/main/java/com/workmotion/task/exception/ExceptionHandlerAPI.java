package com.workmotion.task.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerAPI {

	private static final String INTERNAL_SERVER_ERROR_CODE="500";
	
	@ExceptionHandler(InvalidStateTransactionException.class)
	public ResponseEntity<?> HandleInvalidStateTransactionException(
			InvalidStateTransactionException exception, WebRequest request){
		
		ErrorDetails errorDetails = ErrorDetails.builder().message(exception.getMessage()).code(exception.getCode()).build();
		
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> HandleGlobelException(
			Exception exception, WebRequest request){
		
		ErrorDetails errorDetails = ErrorDetails.builder().message(exception.getMessage()).code(INTERNAL_SERVER_ERROR_CODE).build();
		
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
