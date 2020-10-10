package com.banking.operations.exception;

public class AccountDebitException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public AccountDebitException(String s) 
    {  
        super(s); 
    } 
}
