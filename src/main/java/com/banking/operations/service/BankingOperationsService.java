package com.banking.operations.service;

import com.banking.operations.entity.AccountsDTO;
import com.banking.operations.exception.AccountDebitException;
import com.banking.operations.exception.AccountDoesNotExistsException;
import com.banking.operations.exception.UserNotFountException;
import com.banking.operations.request.dto.CreditRequestDTO;
import com.banking.operations.request.dto.DebitRequestDTO;
import com.banking.operations.request.dto.ViewAccountDTO;
import com.banking.operations.response.dto.UpdatedAccountDetails;

public interface BankingOperationsService {

	public UpdatedAccountDetails creditAccount(CreditRequestDTO creditRequestDTO) throws AccountDoesNotExistsException,UserNotFountException;
	
	public UpdatedAccountDetails debitAccount(DebitRequestDTO debitRequestDTO) throws AccountDebitException,AccountDoesNotExistsException,UserNotFountException;

	public UpdatedAccountDetails getAccountDetails(ViewAccountDTO viewAccountsDTO) throws AccountDoesNotExistsException,UserNotFountException;
}
