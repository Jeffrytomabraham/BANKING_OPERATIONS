package com.banking.operations.request.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class CreditRequestDTO {

	private String username;
	private String accountNumber;
	private Number creditAmount;
}
