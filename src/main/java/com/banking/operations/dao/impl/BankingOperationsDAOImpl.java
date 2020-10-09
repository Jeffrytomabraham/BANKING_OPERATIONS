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
import com.banking.operations.entity.OperationsEntity;

@Component
public class BankingOperationsDAOImpl implements BankingOperationsDAO{

	private Log log = LogFactory.getLog(BankingOperationsDAOImpl.class);
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	public OperationsEntity findUserByUserName(String userName) {
		Query query = new Query();
		query.addCriteria(Criteria.where("userName").is(userName));
		List<OperationsEntity> userDetails= mongoTemplate.find(query, OperationsEntity.class);
		if(userDetails.size()>0) {
			return userDetails.get(0);
		} else {
			return new OperationsEntity();
		}
	}
	
	public OperationsEntity updateAccountDetails(OperationsEntity operationsEntity) {
		log.info("Updating user data");
		log.debug("Entering BankingOperationsDAOImpl.updateAccountDetails for updating user -"+operationsEntity.getUserName());
		
		OperationsEntity savedUserDetails= mongoTemplate.save(operationsEntity);
		
		log.debug("Exiting BankingOperationsDAOImpl.updateAccountDetails after updating user -"+operationsEntity.getUserName());
		log.info("User data saved");
		return savedUserDetails;
	}
}
