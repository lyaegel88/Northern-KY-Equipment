package com.nke.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="NO CATEGORIES FOUND")
public class NoCategoryFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 6809176706655488715L;

}
