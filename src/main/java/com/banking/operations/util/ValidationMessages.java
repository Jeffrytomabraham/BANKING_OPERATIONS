package com.banking.operations.util;

public enum ValidationMessages {

	  LOW_BALANCE("LOW_BALANCE", "Insufficient balance."),
	  INVALID_USER("INVALID_USER", "User not found."),
	  INVALID_ACCOUNT("INVALID_ACCOUNT", "Account not found.");

	  private final String code;
	  private final String description;

	  private ValidationMessages(String code, String description) {
	    this.code = code;
	    this.description = description;
	  }

	  public String getDescription() {
	     return description;
	  }

	  public String getCode() {
	     return code;
	  }

	  @Override
	  public String toString() {
	    return code + ": " + description;
	  }
}
