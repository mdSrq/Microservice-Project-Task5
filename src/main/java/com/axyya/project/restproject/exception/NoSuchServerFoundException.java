package com.axyya.project.restproject.exception;

public class NoSuchServerFoundException extends Exception {
	private static final long serialVersionUID = 1L;

	public NoSuchServerFoundException(String msg) {
		super(msg);
	}
}
