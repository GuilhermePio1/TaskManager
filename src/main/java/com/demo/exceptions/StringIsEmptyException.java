package com.demo.exceptions;

public class StringIsEmptyException extends RuntimeException {
	public StringIsEmptyException(String errorMessage) {
		super(errorMessage);
	}
}
