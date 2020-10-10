package com.banking.operations.service;

import com.banking.operations.exception.AccountDebitException;
import com.banking.operations.request.dto.CreditRequestDTO;
import com.banking.operations.request.dto.DebitRequestDTO;
import com.banking.operations.response.dto.UpdatedAccountDetails;

public interface BankingOperationsService {

	public UpdatedAccountDetails creditAccount(CreditRequestDTO creditRequestDTO);
	
	public UpdatedAccountDetails debitAccount(DebitRequestDTO debitRequestDTO) throws AccountDebitException;
}
