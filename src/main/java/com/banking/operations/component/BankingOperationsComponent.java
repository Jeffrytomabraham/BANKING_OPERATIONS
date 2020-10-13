package com.banking.operations.component;

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

	@Autowired
	BankingOperationsService bankingOperationsService;
	
	public UpdatedAccountDetails creditAccount(CreditRequestDTO creditRequestDTO) {
		try {
			return bankingOperationsService.creditAccount(creditRequestDTO);
		} catch(AccountDoesNotExistsException ex) {
			return setAccountDoesnotExistsExceptionMessage(ex);
		} catch(UserNotFountException ex) {
			return setUserNotFoundExceptionMessage(ex);
		}
	}
	
	public UpdatedAccountDetails debitAccount(DebitRequestDTO debitRequestDTO){
		try {
			return bankingOperationsService.debitAccount(debitRequestDTO);	
		} catch(AccountDoesNotExistsException ex) {
			return setAccountDoesnotExistsExceptionMessage(ex);
		} catch(UserNotFountException ex) {
			return setUserNotFoundExceptionMessage(ex);
		} catch(AccountDebitException ex) {
			return setAccountDebitExceptionMessage(ex);
		}
	}
	
	public UpdatedAccountDetails getAccountDetails(ViewAccountDTO viewAccountsDTO) {
		try {
			return bankingOperationsService.getAccountDetails(viewAccountsDTO);
		} catch(AccountDoesNotExistsException ex) {
			return setAccountDoesnotExistsExceptionMessage(ex);
		} catch(UserNotFountException ex) {
			return setUserNotFoundExceptionMessage(ex);
		}
	}
	
	private UpdatedAccountDetails setUserNotFoundExceptionMessage(UserNotFountException ex) {
		UpdatedAccountDetails updatedAccountDetails = new UpdatedAccountDetails();
		ErrorResponse error = new ErrorResponse();
		error.setErrorCode(ValidationMessages.INVALID_USER.getCode());
		error.setMessage(ex.getMessage());
		updatedAccountDetails.setError(error);
		return updatedAccountDetails;
	}
	
	private UpdatedAccountDetails setAccountDoesnotExistsExceptionMessage(AccountDoesNotExistsException ex) {
		UpdatedAccountDetails updatedAccountDetails = new UpdatedAccountDetails();
		ErrorResponse error = new ErrorResponse();
		error.setErrorCode(ValidationMessages.INVALID_ACCOUNT.getCode());
		error.setMessage(ex.getMessage());
		updatedAccountDetails.setError(error);
		return updatedAccountDetails;
	}
	
	private UpdatedAccountDetails setAccountDebitExceptionMessage(AccountDebitException ex) {
		UpdatedAccountDetails updatedAccountDetails = new UpdatedAccountDetails();
		ErrorResponse error = new ErrorResponse();
		error.setErrorCode(ValidationMessages.LOW_BALANCE.getCode());
		error.setMessage(ex.getMessage());
		updatedAccountDetails.setError(error);
		return updatedAccountDetails;
	}
	 
}
