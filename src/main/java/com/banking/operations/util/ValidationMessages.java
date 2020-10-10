package com.banking.operations.util;

public enum ValidationMessages {

	  LOW_BALANCE("LOW_BALANCE", "Insufficient balance.");

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
