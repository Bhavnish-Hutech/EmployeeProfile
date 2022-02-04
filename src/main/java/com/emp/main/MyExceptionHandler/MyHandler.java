package com.emp.main.MyExceptionHandler;


import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.emp.main.CustomExceptions.ErrorDetails;
import com.emp.main.CustomExceptions.ExistException;
import com.emp.main.CustomExceptions.ResourceNotFoundException;

@ControllerAdvice
public class MyHandler {
	
	
	@ExceptionHandler(ExistException.class)
	public ResponseEntity<?> handleExistException(ExistException exception, WebRequest request ){
	  ErrorDetails errordetails = new ErrorDetails(new Date(), exception.getMessage(), request.getDescription(false));
		return new ResponseEntity<Object>(errordetails , HttpStatus.BAD_REQUEST);
	}
	

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest request ){
	  ErrorDetails errordetails = new ErrorDetails(new Date(), exception.getMessage(), request.getDescription(false));
		return new ResponseEntity<Object>(errordetails , HttpStatus.NOT_FOUND);

	  
	}


	
}



