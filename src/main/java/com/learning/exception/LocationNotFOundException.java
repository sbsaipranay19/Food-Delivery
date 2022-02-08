package com.learning.exception;

import lombok.ToString;

@ToString(callSuper = true)
public class LocationNotFOundException extends Exception {
	public LocationNotFOundException(String msg) {
		super(msg);
	}
}
