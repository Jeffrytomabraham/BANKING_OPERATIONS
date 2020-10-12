package com.banking.operations.exception;

public class UserNotFountException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public UserNotFountException(String s) 
    {  
        super(s); 
    } 
}
