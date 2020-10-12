package com.banking.operations.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AccountsDTOTest {

    private AccountsDTO accountsDTOUnderTest;

    @Before
    public void setUp() {
        accountsDTOUnderTest = new AccountsDTO();
    }

    @Test
    public void setAccountDTO() {
        LocalDateTime date = LocalDateTime.now();
        accountsDTOUnderTest.setAccountNumber("123");
        accountsDTOUnderTest.setAccountType("SAVINGS");
        accountsDTOUnderTest.setBalance(100);
        accountsDTOUnderTest.setCreationDate(date);
        accountsDTOUnderTest.setDueAmount(0);

        Assert.assertEquals(accountsDTOUnderTest.getAccountNumber(), "123");
        Assert.assertEquals(accountsDTOUnderTest.getAccountType(), "SAVINGS");
        Assert.assertTrue(accountsDTOUnderTest.getBalance() == 100);
        Assert.assertEquals(accountsDTOUnderTest.getCreationDate(), date);
        Assert.assertTrue(accountsDTOUnderTest.getDueAmount() == 0);
    }
}
