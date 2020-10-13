package com.banking.operations.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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

	private Log log = LogFactory.getLog(BankingOperationsServiceImpl.class);
			
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	BankingOperationsDAO bankingOperationsDAO;
	
	public UpdatedAccountDetails creditAccount(CreditRequestDTO creditRequestDTO) throws AccountDoesNotExistsException,UserNotFountException {
		log.info("Entering BankingOperationsServiceImpl.creditAccount ");
		log.debug("Entering BankingOperationsServiceImpl.creditAccount for user "+creditRequestDTO.getUsername());
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
			log.debug("Throws invalid account Exception for user "+creditRequestDTO.getUsername());
			throw new AccountDoesNotExistsException(ValidationMessages.INVALID_ACCOUNT.getDescription());
		}
		operationsEntity = bankingOperationsDAO.updateAccountDetails(operationsEntity);
		UpdatedAccountDetails updatedAccountDetails= new UpdatedAccountDetails();
		updatedAccountDetails.setAccountNumber(creditRequestDTO.getAccountNumber());
		updatedAccountDetails.setBalance(balance.get(0));
		log.info("Exiting BankingOperationsServiceImpl.creditAccount ");
		log.debug("Exiting BankingOperationsServiceImpl.creditAccount");
		return updatedAccountDetails;
	}
	
	public UpdatedAccountDetails debitAccount(DebitRequestDTO debitRequestDTO) throws AccountDebitException,AccountDoesNotExistsException,UserNotFountException{
		log.info("Entering BankingOperationsServiceImpl.debitAccount ");
		log.debug("Entering BankingOperationsServiceImpl.debitAccount for user "+debitRequestDTO.getUsername());
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
				log.debug("Low balance for user "+debitRequestDTO.getUsername());
					 throw new AccountDebitException(ValidationMessages.LOW_BALANCE.getDescription());
			}
			acc.setBalance(balanceAFterDebit);
			balance.add(acc.getBalance());
		});
		if(!accountExists.get(0)) {
			log.debug("Specified account doesnot exists for user "+debitRequestDTO.getUsername());
			throw new AccountDoesNotExistsException(ValidationMessages.INVALID_ACCOUNT.getDescription());
		}
		operationsEntity = bankingOperationsDAO.updateAccountDetails(operationsEntity);
		
		updatedAccountDetails.setAccountNumber(debitRequestDTO.getAccountNumber());
		updatedAccountDetails.setBalance(balance.get(0));
		log.info("Exiting BankingOperationsServiceImpl.debitAccount ");
		log.debug("Entering BankingOperationsServiceImpl.debitAccount");
		return updatedAccountDetails;
	}
	
	public UpdatedAccountDetails getAccountDetails(ViewAccountDTO viewAccountsDTO) throws AccountDoesNotExistsException,UserNotFountException{
		log.info("Entering BankingOperationsServiceImpl.getAccountDetails ");
		log.debug("Entering BankingOperationsServiceImpl.getAccountDetails for user "+viewAccountsDTO.getUsername());
		UpdatedAccountDetails updatedAccountDetails= new UpdatedAccountDetails();
		UserDetailsEntityDTO userDetailsEntity = getUserDetails(viewAccountsDTO.getUsername());
		List<AccountsDTO> accounts = new ArrayList<>();
		userDetailsEntity.getAccounts().stream().filter(acc-> acc.getAccountNumber().equals(viewAccountsDTO.getAccountNumber()))
		.forEach(acc->{
			accounts.add(acc);
			});
		if(accounts.isEmpty()) {
			log.debug("Specified account doesnot exists for user "+viewAccountsDTO.getUsername());
			throw new AccountDoesNotExistsException(ValidationMessages.INVALID_ACCOUNT.getDescription());
		}
		updatedAccountDetails.setAccountNumber(accounts.get(0).getAccountNumber());
		updatedAccountDetails.setAccountType(accounts.get(0).getAccountType());
		updatedAccountDetails.setBalance(accounts.get(0).getBalance());
		updatedAccountDetails.setCreationDate(accounts.get(0).getCreationDate());
		updatedAccountDetails.setDueAmount(accounts.get(0).getDueAmount());
		log.info("Exiting BankingOperationsServiceImpl.getAccountDetails ");
		log.debug("Exiting BankingOperationsServiceImpl.getAccountDetails for user "+viewAccountsDTO.getUsername());
		return updatedAccountDetails;
	}
	
	private UserDetailsEntityDTO getUserDetails(String username) {
		UserDetailsEntityDTO operationsEntity = bankingOperationsDAO.findUserByUserName(username);
		if(operationsEntity==null) {
			log.debug("INVALID USER DATA ");
			throw new UserNotFountException(ValidationMessages.INVALID_USER.getDescription());
		}
		return operationsEntity;
	}
	
}
