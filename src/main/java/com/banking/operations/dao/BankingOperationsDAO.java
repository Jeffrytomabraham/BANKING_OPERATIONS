package com.banking.operations.dao;

import com.banking.operations.entity.OperationsEntity;

public interface BankingOperationsDAO {

	public OperationsEntity findUserByUserName(String userName);
	
	public OperationsEntity updateAccountDetails(OperationsEntity operationsEntity);
	
}
