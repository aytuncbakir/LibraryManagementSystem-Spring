package com.aytuncbakir.lms.exception;

public class UserBlockedException extends Exception{

	
	private static final long serialVersionUID = 1L;

	public UserBlockedException() {}
	
	public UserBlockedException(String errorDescription) {
		super(errorDescription);
	}
}
