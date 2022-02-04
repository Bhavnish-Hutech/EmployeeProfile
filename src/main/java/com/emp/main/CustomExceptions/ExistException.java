package com.emp.main.CustomExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)

public class ExistException extends RuntimeException{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	  public ExistException(String message) {
			super(message);
			
		}

}
