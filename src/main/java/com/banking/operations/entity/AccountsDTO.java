package com.banking.operations.entity;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AccountsDTO {

	private String accountType;
	private String accountNumber;
	private Number balance;
	private LocalDateTime creationDate;
	private Number dueAmount;
}
