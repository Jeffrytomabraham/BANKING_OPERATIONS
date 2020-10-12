package com.banking.operations.response.dto;

import java.time.LocalDateTime;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UpdatedAccountDetailsTest {

    private UpdatedAccountDetails updatedAccountDetailsUnderTest;

    @Before
    public void setUp() {
        updatedAccountDetailsUnderTest = new UpdatedAccountDetails();
    }

    @Test
    public void setUpdatedAccountDetails(){
    	LocalDateTime dateTime = LocalDateTime.now();
        updatedAccountDetailsUnderTest.setAccountNumber("123");
        updatedAccountDetailsUnderTest.setBalance(100);
        updatedAccountDetailsUnderTest.setError(new ErrorResponse());
        updatedAccountDetailsUnderTest.setAccountType("Savings");
        updatedAccountDetailsUnderTest.setDueAmount(0);
        updatedAccountDetailsUnderTest.setCreationDate(dateTime);
        
        Assert.assertEquals(updatedAccountDetailsUnderTest.getAccountNumber(), "123");
        Assert.assertTrue(updatedAccountDetailsUnderTest.getBalance() == 100);
        Assert.assertNotNull(updatedAccountDetailsUnderTest.getError());
        Assert.assertTrue(updatedAccountDetailsUnderTest.getDueAmount() == 0);
        Assert.assertEquals(updatedAccountDetailsUnderTest.getCreationDate(), dateTime);
        Assert.assertEquals(updatedAccountDetailsUnderTest.getAccountType(), "Savings");
    }
}
