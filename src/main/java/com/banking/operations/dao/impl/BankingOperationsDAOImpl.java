package com.banking.operations.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.banking.operations.dao.BankingOperationsDAO;
import com.banking.operations.entity.UserDetailsEntityDTO;

@Component
public class BankingOperationsDAOImpl implements BankingOperationsDAO{

	private Log log = LogFactory.getLog(BankingOperationsDAOImpl.class);
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	public UserDetailsEntityDTO findUserByUserName(String userName) {
		log.info("Entering BankingOperationsDAOImpl.findUserByUserName");
		log.debug("Entering BankingOperationsDAOImpl.findUserByUserName ");
		Query query = new Query();
		query.addCriteria(Criteria.where("userName").is(userName));
		List<UserDetailsEntityDTO> userDetails= mongoTemplate.find(query, UserDetailsEntityDTO.class);
		log.info("Exiting BankingOperationsDAOImpl.findUserByUserName");
		if(userDetails.size()>0) {
			return userDetails.get(0);
		} else {
			log.info("Exiting BankingOperationsDAOImpl.findUserByUserName");
			return null;
		}
	}
	
	public UserDetailsEntityDTO updateAccountDetails(UserDetailsEntityDTO userDetails) {
		log.info("Entering BankingOperationsDAOImpl.updateAccountDetails");
		log.debug("Entering BankingOperationsDAOImpl.updateAccountDetails for updating user -"+userDetails.getUserName());
		
		UserDetailsEntityDTO savedUserDetails= mongoTemplate.save(userDetails);
		
		log.debug("Exiting BankingOperationsDAOImpl.updateAccountDetails after updating user -"+userDetails.getUserName());
		log.info("Exiting BankingOperationsDAOImpl.updateAccountDetails");
		return savedUserDetails;
	}
}
