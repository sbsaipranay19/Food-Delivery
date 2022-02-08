package com.learning.exception;

public class MovieNotFoundException extends Exception {
	public MovieNotFoundException(String msg) {
		super(msg);
	}
}
