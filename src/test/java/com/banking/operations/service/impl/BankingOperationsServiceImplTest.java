package com.banking.operations.service.impl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import com.banking.operations.dao.BankingOperationsDAO;
import com.banking.operations.entity.AccountsDTO;
import com.banking.operations.entity.UserDetailsEntityDTO;
import com.banking.operations.exception.AccountDebitException;
import com.banking.operations.request.dto.CreditRequestDTO;
import com.banking.operations.request.dto.DebitRequestDTO;
import com.banking.operations.response.dto.UpdatedAccountDetails;

@RunWith(MockitoJUnitRunner.Silent.class)
public class BankingOperationsServiceImplTest {

    private BankingOperationsServiceImpl bankingOperationsServiceImplUnderTest;
    UserDetailsEntityDTO userDetailsEntityDTO = new UserDetailsEntityDTO();

    @Before
    public void setUp() {
        bankingOperationsServiceImplUnderTest = new BankingOperationsServiceImpl();
        bankingOperationsServiceImplUnderTest.modelMapper = mock(ModelMapper.class);
        bankingOperationsServiceImplUnderTest.bankingOperationsDAO = mock(BankingOperationsDAO.class);
         
        userDetailsEntityDTO.setAddress1("temp1");
        userDetailsEntityDTO.setAddress2("temp1");
        userDetailsEntityDTO.setAge(10);
        userDetailsEntityDTO.setCountry("India");
        AccountsDTO account = new AccountsDTO();
        account.setAccountNumber("123");
        account.setAccountType("SAVINGS");
        account.setBalance(1000);
        List<AccountsDTO> accountList = new ArrayList<>();
        accountList.add(account);
        userDetailsEntityDTO.setAccounts(accountList);
        
    }

    @Test
    public void testCreditAccount() {
        // Setup
        CreditRequestDTO creditRequestDTO = new CreditRequestDTO();
        creditRequestDTO.setAccountNumber("123");
        creditRequestDTO.setCreditAmount(500);
        creditRequestDTO.setUsername("username");

        // Configure BankingOperationsDAO.findUserByUserName(...).
        when(bankingOperationsServiceImplUnderTest.bankingOperationsDAO.findUserByUserName(anyString())).thenReturn(userDetailsEntityDTO);

        // Configure BankingOperationsDAO.updateAccountDetails(...).
        when(bankingOperationsServiceImplUnderTest.bankingOperationsDAO.updateAccountDetails(any(UserDetailsEntityDTO.class))).thenReturn(userDetailsEntityDTO);

        // Run the test
        final UpdatedAccountDetails result = bankingOperationsServiceImplUnderTest.creditAccount(creditRequestDTO);

        // Verify the results
        Assert.assertNotNull(result);
        Assert.assertNull(result.getError());
        Assert.assertEquals(result.getAccountNumber(),"123");
    }

    @Test
    public void testDebitAccount() {
        // Setup
        DebitRequestDTO debitRequestDTO = new DebitRequestDTO();
        debitRequestDTO.setAccountNumber("123");
        debitRequestDTO.setDebitAmount(500);
        debitRequestDTO.setUsername("username");
        // Configure BankingOperationsDAO.findUserByUserName(...).
        when(bankingOperationsServiceImplUnderTest.bankingOperationsDAO.findUserByUserName(anyString())).thenReturn(userDetailsEntityDTO);

        // Configure BankingOperationsDAO.updateAccountDetails(...).
        when(bankingOperationsServiceImplUnderTest.bankingOperationsDAO.updateAccountDetails(any(UserDetailsEntityDTO.class))).thenReturn(userDetailsEntityDTO);

        // Run the test
        final UpdatedAccountDetails result = bankingOperationsServiceImplUnderTest.debitAccount(debitRequestDTO);

        // Verify the results
        Assert.assertNotNull(result);
        Assert.assertNull(result.getError());
        Assert.assertEquals(result.getAccountNumber(),"123");
        
    }

    @Test(expected = AccountDebitException.class)
    public void testDebitAccount_ThrowsAccountDebitException() {
        // Setup
        DebitRequestDTO debitRequestDTO = new DebitRequestDTO();
        debitRequestDTO.setAccountNumber("123");
        debitRequestDTO.setDebitAmount(5000);
        debitRequestDTO.setUsername("username");
        // Configure BankingOperationsDAO.findUserByUserName(...).
        when(bankingOperationsServiceImplUnderTest.bankingOperationsDAO.findUserByUserName(anyString())).thenReturn(userDetailsEntityDTO);

        // Configure BankingOperationsDAO.updateAccountDetails(...).
        when(bankingOperationsServiceImplUnderTest.bankingOperationsDAO.updateAccountDetails(any(UserDetailsEntityDTO.class))).thenReturn(userDetailsEntityDTO);

        // Run the test
        bankingOperationsServiceImplUnderTest.debitAccount(debitRequestDTO);
    }
}
