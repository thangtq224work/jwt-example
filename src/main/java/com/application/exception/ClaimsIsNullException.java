package com.application.exception;

public class ClaimsIsNullException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	String message;
	public ClaimsIsNullException(String mess) {
		super(mess);
	}
}
