package com.learning.exception;

import lombok.ToString;

@ToString(callSuper = true)
public class InvalidAmountException extends Exception {
	public InvalidAmountException(String msg) {
		super(msg);
	}
}
