package com.banking.operations.response.dto;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdatedAccountDetails {

	private String accountType;
	private String accountNumber;
	private double balance;
	private LocalDateTime creationDate;
	private double dueAmount;
	private ErrorResponse error;
}
