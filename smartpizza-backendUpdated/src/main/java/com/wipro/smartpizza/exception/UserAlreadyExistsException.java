package com.wipro.smartpizza.exception;

public class UserAlreadyExistsException  extends RuntimeException{
	
	public UserAlreadyExistsException(String message) {
		super(message);
	}

}
