package com.banking.operations.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.operations.dao.BankingOperationsDAO;
import com.banking.operations.entity.UserDetailsEntityDTO;
import com.banking.operations.exception.AccountDebitException;
import com.banking.operations.request.dto.CreditRequestDTO;
import com.banking.operations.request.dto.DebitRequestDTO;
import com.banking.operations.response.dto.UpdatedAccountDetails;
import com.banking.operations.service.BankingOperationsService;
import com.banking.operations.util.ValidationMessages;

@Service
public class BankingOperationsServiceImpl implements BankingOperationsService{

	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	BankingOperationsDAO bankingOperationsDAO;
	
	public UpdatedAccountDetails creditAccount(CreditRequestDTO creditRequestDTO) {
		UserDetailsEntityDTO operationsEntity = bankingOperationsDAO.findUserByUserName(creditRequestDTO.getUsername());
		List<Double> balance= new ArrayList<>();
		operationsEntity.getAccounts().stream().filter(acc-> acc.getAccountNumber().equals(creditRequestDTO.getAccountNumber()))
		.forEach(acc->{
			acc.setBalance(acc.getBalance() + creditRequestDTO.getCreditAmount());
			balance.add(acc.getBalance());
		});
		operationsEntity = bankingOperationsDAO.updateAccountDetails(operationsEntity);
		UpdatedAccountDetails updatedAccountDetails= new UpdatedAccountDetails();
		updatedAccountDetails.setAccountNumber(creditRequestDTO.getAccountNumber());
		updatedAccountDetails.setBalance(balance.get(0));
		updatedAccountDetails.setSuccess(true);
		return updatedAccountDetails;
	}
	
	public UpdatedAccountDetails debitAccount(DebitRequestDTO debitRequestDTO) throws AccountDebitException{
		UpdatedAccountDetails updatedAccountDetails= new UpdatedAccountDetails();
		UserDetailsEntityDTO operationsEntity = bankingOperationsDAO.findUserByUserName(debitRequestDTO.getUsername());
		List<Double> balance= new ArrayList<>();
		operationsEntity.getAccounts().stream().filter(acc-> acc.getAccountNumber().equals(debitRequestDTO.getAccountNumber()))
		.forEach(acc->{
			double balanceAFterDebit = acc.getBalance() - debitRequestDTO.getDebitAmount();
			if(balanceAFterDebit<0) {		
					 throw new AccountDebitException(ValidationMessages.LOW_BALANCE.getDescription());
			}
			acc.setBalance(balanceAFterDebit);
			balance.add(acc.getBalance());
		});
		operationsEntity = bankingOperationsDAO.updateAccountDetails(operationsEntity);
		
		updatedAccountDetails.setAccountNumber(debitRequestDTO.getAccountNumber());
		updatedAccountDetails.setBalance(balance.get(0));
		updatedAccountDetails.setSuccess(true);
		return updatedAccountDetails;
	}
}
