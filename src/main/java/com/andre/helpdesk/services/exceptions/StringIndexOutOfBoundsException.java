package com.andre.helpdesk.services.exceptions;

public class StringIndexOutOfBoundsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public StringIndexOutOfBoundsException(String message, Throwable cause) {
		super(message, cause);
	}

	public StringIndexOutOfBoundsException(String message) {
		super(message);
	}

}
