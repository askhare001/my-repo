package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(CustomerException.class)
	public ResponseEntity<String> handleCustomerException(CustomerException e)
	{
		return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
	}

}
