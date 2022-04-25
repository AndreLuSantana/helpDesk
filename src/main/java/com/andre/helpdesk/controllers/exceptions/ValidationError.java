package com.andre.helpdesk.controllers.exceptions;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {

	private static final long serialVersionUID = 1L;

	private List<FieldMessages> errors = new ArrayList<>();

	public ValidationError(Instant timestamp, Integer status, String error, String message, String path) {
		super(timestamp, status, error, message, path);
	}

	public ValidationError(List<FieldMessages> errors) {
		super();
		this.errors = errors;
	}

	public List<FieldMessages> getErrors() {
		return errors;
	}

	public void addError(String fieldName, String message) {
		this.errors.add(new FieldMessages(fieldName, message));
	}

}
