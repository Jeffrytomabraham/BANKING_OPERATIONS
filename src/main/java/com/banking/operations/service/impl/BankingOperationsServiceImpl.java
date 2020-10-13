package com.banking.operations.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.operations.dao.BankingOperationsDAO;
import com.banking.operations.entity.AccountsDTO;
import com.banking.operations.entity.UserDetailsEntityDTO;
import com.banking.operations.exception.AccountDebitException;
import com.banking.operations.exception.AccountDoesNotExistsException;
import com.banking.operations.exception.UserNotFountException;
import com.banking.operations.request.dto.CreditRequestDTO;
import com.banking.operations.request.dto.DebitRequestDTO;
import com.banking.operations.request.dto.ViewAccountDTO;
import com.banking.operations.response.dto.UpdatedAccountDetails;
import com.banking.operations.service.BankingOperationsService;
import com.banking.operations.util.ValidationMessages;

@Service
public class BankingOperationsServiceImpl implements BankingOperationsService{

	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	BankingOperationsDAO bankingOperationsDAO;
	
	public UpdatedAccountDetails creditAccount(CreditRequestDTO creditRequestDTO) throws AccountDoesNotExistsException,UserNotFountException {
		UserDetailsEntityDTO operationsEntity = getUserDetails(creditRequestDTO.getUsername());
		List<Boolean> accountExists = new ArrayList<>();
		accountExists.add(false);
		List<Double> balance= new ArrayList<>();
		operationsEntity.getAccounts().stream().filter(acc-> acc.getAccountNumber().equals(creditRequestDTO.getAccountNumber()))
		.forEach(acc->{
			acc.setBalance(acc.getBalance() + creditRequestDTO.getCreditAmount());
			balance.add(acc.getBalance());
			accountExists.add(0, true);
		});
		if(!accountExists.get(0)) {
			throw new AccountDoesNotExistsException(ValidationMessages.INVALID_ACCOUNT.getDescription());
		}
		operationsEntity = bankingOperationsDAO.updateAccountDetails(operationsEntity);
		UpdatedAccountDetails updatedAccountDetails= new UpdatedAccountDetails();
		updatedAccountDetails.setAccountNumber(creditRequestDTO.getAccountNumber());
		updatedAccountDetails.setBalance(balance.get(0));
		return updatedAccountDetails;
	}
	
	public UpdatedAccountDetails debitAccount(DebitRequestDTO debitRequestDTO) throws AccountDebitException,AccountDoesNotExistsException,UserNotFountException{
		UpdatedAccountDetails updatedAccountDetails= new UpdatedAccountDetails();
		UserDetailsEntityDTO operationsEntity = getUserDetails(debitRequestDTO.getUsername());
		List<Boolean> accountExists = new ArrayList<>();
		accountExists.add(false);
		List<Double> balance= new ArrayList<>();
		operationsEntity.getAccounts().stream().filter(acc-> acc.getAccountNumber().equals(debitRequestDTO.getAccountNumber()))
		.forEach(acc->{
			double balanceAFterDebit = acc.getBalance() - debitRequestDTO.getDebitAmount();
			balance.add(acc.getBalance());
			accountExists.add(0, true);
			if(balanceAFterDebit<0) {		
					 throw new AccountDebitException(ValidationMessages.LOW_BALANCE.getDescription());
			}
			acc.setBalance(balanceAFterDebit);
			balance.add(acc.getBalance());
		});
		if(!accountExists.get(0)) {
			throw new AccountDoesNotExistsException(ValidationMessages.INVALID_ACCOUNT.getDescription());
		}
		operationsEntity = bankingOperationsDAO.updateAccountDetails(operationsEntity);
		
		updatedAccountDetails.setAccountNumber(debitRequestDTO.getAccountNumber());
		updatedAccountDetails.setBalance(balance.get(0));
		return updatedAccountDetails;
	}
	
	public UpdatedAccountDetails getAccountDetails(ViewAccountDTO viewAccountsDTO) throws AccountDoesNotExistsException,UserNotFountException{
		UpdatedAccountDetails updatedAccountDetails= new UpdatedAccountDetails();
		UserDetailsEntityDTO userDetailsEntity = getUserDetails(viewAccountsDTO.getUsername());
		List<AccountsDTO> accounts = new ArrayList<>();
		userDetailsEntity.getAccounts().stream().filter(acc-> acc.getAccountNumber().equals(viewAccountsDTO.getAccountNumber()))
		.forEach(acc->{
			accounts.add(acc);
			});
		if(accounts.isEmpty()) {
			throw new AccountDoesNotExistsException(ValidationMessages.INVALID_ACCOUNT.getDescription());
		}
		updatedAccountDetails.setAccountNumber(accounts.get(0).getAccountNumber());
		updatedAccountDetails.setAccountType(accounts.get(0).getAccountType());
		updatedAccountDetails.setBalance(accounts.get(0).getBalance());
		updatedAccountDetails.setCreationDate(accounts.get(0).getCreationDate());
		updatedAccountDetails.setDueAmount(accounts.get(0).getDueAmount());
		return updatedAccountDetails;
	}
	
	private UserDetailsEntityDTO getUserDetails(String username) {
		UserDetailsEntityDTO operationsEntity = bankingOperationsDAO.findUserByUserName(username);
		if(operationsEntity==null) {
			throw new UserNotFountException(ValidationMessages.INVALID_USER.getDescription());
		}
		return operationsEntity;
	}
	
}
