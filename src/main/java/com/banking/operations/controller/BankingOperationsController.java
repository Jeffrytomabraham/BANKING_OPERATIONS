package com.banking.operations.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.banking.operations.component.BankingOperationsComponent;
import com.banking.operations.request.dto.CreditRequestDTO;
import com.banking.operations.request.dto.DebitRequestDTO;
import com.banking.operations.request.dto.ViewAccountDTO;
import com.banking.operations.response.dto.UpdatedAccountDetails;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/banking/operations")
public class BankingOperationsController {
	
	private Log log = LogFactory.getLog(BankingOperationsController.class);
	
	@Autowired
	BankingOperationsComponent bankingOperationsComponent;
	
	@PostMapping(value= {"/credit"},
			consumes="application/json",produces="application/json")
	@ApiOperation(value = "Add credit to account")
	public @ResponseBody ResponseEntity<?> creditOperation(@RequestBody CreditRequestDTO creditRequestDTO){
		log.info("Entering BankingOperationsController.creditOperation ");
		log.debug("Entering BankingOperationsController.creditOperation ");
		UpdatedAccountDetails updatedAccountDetails = bankingOperationsComponent.creditAccount(creditRequestDTO);
		HttpStatus status = HttpStatus.OK;
		if(updatedAccountDetails.getError()!=null) {
			status = HttpStatus.BAD_REQUEST;
		}
		log.info("Exiting BankingOperationsController.creditOperation ");
		log.debug("Exiting BankingOperationsController.creditOperation ");
		return new ResponseEntity<>(updatedAccountDetails,status);
	}
	
	@PostMapping(value= {"/debit"},
			consumes="application/json",produces="application/json")
	@ApiOperation(value = "Debit customer account")
	public @ResponseBody ResponseEntity<?> debitOperation(@RequestBody DebitRequestDTO debitRequestDTO){
		log.info("Entering BankingOperationsController.debitOperation ");
		HttpStatus status = HttpStatus.OK;
		UpdatedAccountDetails updatedAccountDetails = bankingOperationsComponent.debitAccount(debitRequestDTO);
		if(updatedAccountDetails.getError()!=null) {
			status = HttpStatus.BAD_REQUEST;
		}
		log.info("Exiting BankingOperationsController.debitOperation ");
		return new ResponseEntity<>(updatedAccountDetails,status);
	}		
	
	@PostMapping(value= {"/account/details"},
			consumes="application/json",produces="application/json")
	@ApiOperation(value = "Debit customer account")
	public @ResponseBody ResponseEntity<?> viewAccountDetails(@RequestBody ViewAccountDTO vewAccountDTO){
		log.info("Entering BankingOperationsController.viewAccountDetails ");
		UpdatedAccountDetails updatedAccountDetails = bankingOperationsComponent.getAccountDetails(vewAccountDTO);
		HttpStatus status = HttpStatus.OK;
		if(updatedAccountDetails.getError()!=null) {
			status = HttpStatus.BAD_REQUEST;
		}
		log.info("Exiting BankingOperationsController.viewAccountDetails ");
		return new ResponseEntity<>(updatedAccountDetails,status);
	}

}
