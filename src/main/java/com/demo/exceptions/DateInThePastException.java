package com.demo.exceptions;

public class DateInThePastException extends RuntimeException {
	public DateInThePastException(String errorMessage) {
		super(errorMessage);
	}
}
