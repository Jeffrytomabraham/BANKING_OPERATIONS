package com.banking.operations.request.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class DebitRequestDTO {

	private String username;
	private String accountNumber;
	private Number creditAmount;
}