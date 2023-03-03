package com.example.book.exception;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.book.model.ApiError;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
     
	@ExceptionHandler({ BookNotFoundException.class })
	protected ResponseEntity<Object> handleBookNotFound(Exception ex) {
		ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, ex.getMessage());
		return buildResponseEntity(apiError);
	}

	private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
		
		return new ResponseEntity<>(apiError, apiError.getStatus());
		
	}
	
}
