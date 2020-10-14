package com.banking.operations.dao.impl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.banking.operations.entity.AccountsDTO;
import com.banking.operations.entity.UserDetailsEntityDTO;

@RunWith(MockitoJUnitRunner.class)
public class BankingOperationsDAOImplTest {

    private BankingOperationsDAOImpl bankingOperationsDAOImplUnderTest;

    @BeforeEach
    public void setUp() {
        bankingOperationsDAOImplUnderTest = new BankingOperationsDAOImpl();
        bankingOperationsDAOImplUnderTest.mongoTemplate = mock(MongoTemplate.class);
    }


    @Test
    public void testUpdateAccountDetails() {
        final UserDetailsEntityDTO userDetails = new UserDetailsEntityDTO();
        userDetails.setAddress1("temp1");
        userDetails.setAddress2("temp2");
        userDetails.setAge(20);
        userDetails.setCity("tvm");
        AccountsDTO accounts = new AccountsDTO();
        accounts.setAccountNumber("123");
        accounts.setAccountType("savings");
        accounts.setBalance(500);
        userDetails.setAccounts(Arrays.asList(accounts));

        final UserDetailsEntityDTO userDetailsEntityDTO = new UserDetailsEntityDTO();
        when(bankingOperationsDAOImplUnderTest.mongoTemplate.save(any(UserDetailsEntityDTO.class))).thenReturn(userDetailsEntityDTO);

        final UserDetailsEntityDTO result = bankingOperationsDAOImplUnderTest.updateAccountDetails(userDetails);


        Assert.assertNotNull(result);
        Assert.assertEquals(userDetails.getAddress1(), "temp1");
        Assert.assertEquals(userDetails.getAddress2(), "temp2");
        Assert.assertEquals(userDetails.getCity(),"tvm");
        Assert.assertTrue(userDetails.getAccounts().size()>0);

    }
}
