package com.banking.operations.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.banking.operations.exception.AccountDebitException;
import com.banking.operations.request.dto.CreditRequestDTO;
import com.banking.operations.request.dto.DebitRequestDTO;
import com.banking.operations.response.dto.UpdatedAccountDetails;
import com.banking.operations.service.BankingOperationsService;

@Component
public class BankingOperationsComponent {

	@Autowired
	BankingOperationsService bankingOperationsService;
	
	public UpdatedAccountDetails creditAccount(CreditRequestDTO creditRequestDTO) {
		return bankingOperationsService.creditAccount(creditRequestDTO);
	}
	
	public UpdatedAccountDetails debitAccount(DebitRequestDTO debitRequestDTO) throws AccountDebitException{
		
			return bankingOperationsService.debitAccount(debitRequestDTO);
		
	}
	 
}
