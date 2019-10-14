package com.nhduong29.panasiada.dashboard.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class GeneralException extends RuntimeException {
	private static final long serialVersionUID = 8443755364163723939L;

	public GeneralException(String message) {
		super(message);
	}

	public GeneralException(String message, Throwable cause) {
		super(message, cause);
	}
}
