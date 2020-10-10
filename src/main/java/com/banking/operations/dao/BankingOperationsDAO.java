package com.banking.operations.dao;

import com.banking.operations.entity.UserDetailsEntityDTO;

public interface BankingOperationsDAO {

	public UserDetailsEntityDTO findUserByUserName(String userName);
	
	public UserDetailsEntityDTO updateAccountDetails(UserDetailsEntityDTO userDetails);
	
}
