package com.reactive.profile.custom.exception;


import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class BusinessException extends RuntimeException   {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String errorCode;
	private String errorMessage;
	
	public BusinessException(String errorCode, String errorMessage) {
		super("profile API ::"+errorCode + "::" + errorMessage);
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		
	}
	
	public BusinessException() {
		super("something went wrong");
	}
	
	public BusinessException(String errorCode) {
		super("Error userID:" + errorCode + " is not found.");
	}


}

