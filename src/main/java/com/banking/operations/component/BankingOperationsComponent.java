package com.banking.operations.component;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.banking.operations.exception.AccountDebitException;
import com.banking.operations.exception.AccountDoesNotExistsException;
import com.banking.operations.exception.UserNotFountException;
import com.banking.operations.request.dto.CreditRequestDTO;
import com.banking.operations.request.dto.DebitRequestDTO;
import com.banking.operations.request.dto.ViewAccountDTO;
import com.banking.operations.response.dto.ErrorResponse;
import com.banking.operations.response.dto.UpdatedAccountDetails;
import com.banking.operations.service.BankingOperationsService;
import com.banking.operations.util.ValidationMessages;

@Component
public class BankingOperationsComponent {
	
	private Log log = LogFactory.getLog(BankingOperationsComponent.class);

	@Autowired
	BankingOperationsService bankingOperationsService;
	
	public UpdatedAccountDetails creditAccount(CreditRequestDTO creditRequestDTO) {
		log.info("Entering BankingOperationsComponent.creditAccount ");
		log.debug("Entering BankingOperationsComponent.creditAccount for user "+creditRequestDTO.getUsername());
		try {
			return bankingOperationsService.creditAccount(creditRequestDTO);
		} catch(AccountDoesNotExistsException ex) {
			log.info("AccountDoesNotExistsException caught ");
			log.debug("AccountDoesNotExistsException caught for user "+creditRequestDTO.getUsername());
			return setAccountDoesnotExistsExceptionMessage(ex);
		} catch(UserNotFountException ex) {
			log.info("UserNotFountException caught ");
			log.debug("UserNotFountException caught for user "+creditRequestDTO.getUsername());
			return setUserNotFoundExceptionMessage(ex);
		}
	}
	
	public UpdatedAccountDetails debitAccount(DebitRequestDTO debitRequestDTO){
		log.info("Entering BankingOperationsComponent.debitAccount ");
		log.debug("Entering BankingOperationsComponent.debitAccount for user "+debitRequestDTO.getUsername());
		try {
			return bankingOperationsService.debitAccount(debitRequestDTO);	
		} catch(AccountDoesNotExistsException ex) {
			log.info("AccountDoesNotExistsException caught ");
			log.debug("AccountDoesNotExistsException caught for user "+debitRequestDTO.getUsername());
			return setAccountDoesnotExistsExceptionMessage(ex);
		} catch(UserNotFountException ex) {
			log.info("UserNotFountException caught ");
			log.debug("UserNotFountException caught for user "+debitRequestDTO.getUsername());
			return setUserNotFoundExceptionMessage(ex);
		} catch(AccountDebitException ex) {
			log.info("AccountDebitException caught ");
			log.debug("AccountDebitException caught for user "+debitRequestDTO.getUsername());
			return setAccountDebitExceptionMessage(ex);
		}
	}
	
	public UpdatedAccountDetails getAccountDetails(ViewAccountDTO viewAccountsDTO) {
		log.info("Entering BankingOperationsComponent.debitAccount ");
		log.debug("Entering BankingOperationsComponent.debitAccount for user "+viewAccountsDTO.getUsername());
		try {
			return bankingOperationsService.getAccountDetails(viewAccountsDTO);
		} catch(AccountDoesNotExistsException ex) {
			log.info("AccountDoesNotExistsException caught ");
			log.debug("AccountDoesNotExistsException caught for user "+viewAccountsDTO.getUsername());
			return setAccountDoesnotExistsExceptionMessage(ex);
		} catch(UserNotFountException ex) {
			log.info("UserNotFountException caught ");
			log.debug("UserNotFountException caught for user "+viewAccountsDTO.getUsername());
			return setUserNotFoundExceptionMessage(ex);
		}
	}
	
	private UpdatedAccountDetails setUserNotFoundExceptionMessage(UserNotFountException ex) {
		UpdatedAccountDetails updatedAccountDetails = new UpdatedAccountDetails();
		ErrorResponse error = new ErrorResponse();
		error.setErrorCode(ValidationMessages.INVALID_USER.getCode());
		error.setMessage(ex.getMessage());
		updatedAccountDetails.setError(error);
		log.info("Exiting BankingOperationsComponent ");
		return updatedAccountDetails;
	}
	
	private UpdatedAccountDetails setAccountDoesnotExistsExceptionMessage(AccountDoesNotExistsException ex) {
		UpdatedAccountDetails updatedAccountDetails = new UpdatedAccountDetails();
		ErrorResponse error = new ErrorResponse();
		error.setErrorCode(ValidationMessages.INVALID_ACCOUNT.getCode());
		error.setMessage(ex.getMessage());
		updatedAccountDetails.setError(error);
		log.info("Exiting BankingOperationsComponent ");
		return updatedAccountDetails;
	}
	
	private UpdatedAccountDetails setAccountDebitExceptionMessage(AccountDebitException ex) {
		UpdatedAccountDetails updatedAccountDetails = new UpdatedAccountDetails();
		ErrorResponse error = new ErrorResponse();
		error.setErrorCode(ValidationMessages.LOW_BALANCE.getCode());
		error.setMessage(ex.getMessage());
		updatedAccountDetails.setError(error);
		log.info("Exiting BankingOperationsComponent ");
		return updatedAccountDetails;
	}
	 
}
