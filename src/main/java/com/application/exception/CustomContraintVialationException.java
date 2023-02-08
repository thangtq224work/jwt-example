package com.application.exception;

import lombok.Data;

@Data
public class CustomContraintVialationException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	String message;
	int code;
	public CustomContraintVialationException(String mess) {
		super(mess);
	}
}
