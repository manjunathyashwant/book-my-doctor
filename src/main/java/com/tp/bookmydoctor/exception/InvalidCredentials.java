package com.tp.bookmydoctor.exception;

public class InvalidCredentials extends RuntimeException{
	
	private String message;
	
	public InvalidCredentials(String message) {
		super(message);
	}

}
