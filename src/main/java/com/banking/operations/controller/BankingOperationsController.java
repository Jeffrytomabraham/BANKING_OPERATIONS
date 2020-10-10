package com.banking.operations.controller;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.banking.operations.component.BankingOperationsComponent;
import com.banking.operations.exception.AccountDebitException;
import com.banking.operations.request.dto.CreditRequestDTO;
import com.banking.operations.request.dto.DebitRequestDTO;
import com.banking.operations.response.dto.ErrorResponse;
import com.banking.operations.response.dto.UpdatedAccountDetails;
import com.banking.operations.util.ValidationMessages;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/banking/operations")
public class BankingOperationsController {
	
	private Log log = LogFactory.getLog(BankingOperationsController.class);
	
	@Autowired
	BankingOperationsComponent bankingOperationsComponent;
	
	@RequestMapping(value= {"/credit"},method = RequestMethod.POST,
			consumes="application/json",produces="application/json")
	@ApiOperation(value = "Add credit to account")
	public @ResponseBody ResponseEntity<?> creditOperation(@Valid @RequestBody CreditRequestDTO creditRequestDTO){
		UpdatedAccountDetails updatedAccountDetails = bankingOperationsComponent.creditAccount(creditRequestDTO);
		return new ResponseEntity<>(updatedAccountDetails,HttpStatus.OK);
	}
	
	@RequestMapping(value= {"/debit"},method = RequestMethod.POST,
			consumes="application/json",produces="application/json")
	@ApiOperation(value = "Debit customer account")
	public @ResponseBody ResponseEntity<?> debitOperation(@Valid @RequestBody DebitRequestDTO debitRequestDTO){
		UpdatedAccountDetails updatedAccountDetails = new UpdatedAccountDetails();
		HttpStatus httpStatus = HttpStatus.OK;
		try {
			updatedAccountDetails = bankingOperationsComponent.debitAccount(debitRequestDTO);
		} catch (AccountDebitException e) {
			updatedAccountDetails.setSuccess(false);
			ErrorResponse error = new ErrorResponse();
			error.setErrorCode(ValidationMessages.LOW_BALANCE.getCode());
			error.setMessage(e.getMessage());
			updatedAccountDetails.setError(error);
			httpStatus = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<>(updatedAccountDetails,httpStatus);
	}		

}
