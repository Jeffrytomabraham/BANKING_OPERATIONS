package com.banking.operations.exception;

public class AccountDoesNotExistsException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public AccountDoesNotExistsException(String s) 
    {  
        super(s); 
    } 
}
