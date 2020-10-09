package com.banking.operations.response.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdatedAccountDetails {

	private String accountType;
	private String balance;
	private String isSuccess;
	private LocalDateTime createdDate;
}
