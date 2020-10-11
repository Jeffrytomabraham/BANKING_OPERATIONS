package com.banking.operations.dao.impl;

import com.banking.operations.entity.UserDetailsEntityDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import javax.annotation.security.RunAs;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BankingOperationsDAOImplTest {

    private BankingOperationsDAOImpl bankingOperationsDAOImplUnderTest;

    @Before
    public void setUp() {
        bankingOperationsDAOImplUnderTest = new BankingOperationsDAOImpl();
        bankingOperationsDAOImplUnderTest.mongoTemplate = mock(MongoTemplate.class);
    }

    @Test
    public void testFindUserByUserName() {
        // Setup

        // Configure MongoTemplate.find(...).
        final UserDetailsEntityDTO userDetailsEntityDTO = new UserDetailsEntityDTO();
        final List<UserDetailsEntityDTO> userDetailsEntityDTOS = Arrays.asList(userDetailsEntityDTO);
        when(bankingOperationsDAOImplUnderTest.mongoTemplate.find(new Query(null), UserDetailsEntityDTO.class)).thenReturn(userDetailsEntityDTOS);

        // Run the test
        final UserDetailsEntityDTO result = bankingOperationsDAOImplUnderTest.findUserByUserName("userName");

        // Verify the results
    }

    @Test
    public void testUpdateAccountDetails() {
        // Setup
        final UserDetailsEntityDTO userDetails = new UserDetailsEntityDTO();

        // Configure MongoTemplate.save(...).
        final UserDetailsEntityDTO userDetailsEntityDTO = new UserDetailsEntityDTO();
        when(bankingOperationsDAOImplUnderTest.mongoTemplate.save(any(UserDetailsEntityDTO.class))).thenReturn(userDetailsEntityDTO);

        // Run the test
        final UserDetailsEntityDTO result = bankingOperationsDAOImplUnderTest.updateAccountDetails(userDetails);

        // Verify the results
    }
}
