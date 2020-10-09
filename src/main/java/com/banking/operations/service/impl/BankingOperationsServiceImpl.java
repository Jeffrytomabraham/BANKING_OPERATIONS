package com.banking.operations.service.impl;

import java.text.NumberFormat;
import java.util.Locale;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.banking.operations.dao.BankingOperationsDAO;
import com.banking.operations.entity.OperationsEntity;
import com.banking.operations.request.dto.CreditRequestDTO;
import com.banking.operations.request.dto.DebitRequestDTO;
import com.banking.operations.response.dto.UpdatedAccountDetails;
import com.banking.operations.service.BankingOperationsService;

public class BankingOperationsServiceImpl implements BankingOperationsService{

	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	BankingOperationsDAO bankingOperationsDAO;
	
	public UpdatedAccountDetails creditAccount(CreditRequestDTO creditRequestDTO) {
		OperationsEntity operationsEntity = bankingOperationsDAO.findUserByUserName(creditRequestDTO.getUsername());
		operationsEntity.getAccounts().stream().filter(acc-> acc.getAccountNumber().equals(creditRequestDTO.getAccountNumber()))
		.forEach(acc->{
			NumberFormat nf = NumberFormat.getInstance(Locale.ENGLISH);
			acc.setBalance(Integer.parseInt(nf.format(acc.getBalance())) + Integer.parseInt(nf.format(creditRequestDTO.getCreditAmount())));
		});
		operationsEntity = bankingOperationsDAO.updateAccountDetails(operationsEntity);
		UpdatedAccountDetails updatedAccountDetails= new UpdatedAccountDetails();
		//updatedAccountDetails.setAccountType(creditRequestDTO.get);
		return updatedAccountDetails;
	}
	
	public UpdatedAccountDetails debitAccount(DebitRequestDTO debitRequestDTO) {
		OperationsEntity operationsEntity = bankingOperationsDAO.findUserByUserName(debitRequestDTO.getUsername());
		return null;
	}
}
