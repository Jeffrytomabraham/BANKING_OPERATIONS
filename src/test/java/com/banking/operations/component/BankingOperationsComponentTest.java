package com.banking.operations.component;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.banking.operations.exception.AccountDebitException;
import com.banking.operations.exception.AccountDoesNotExistsException;
import com.banking.operations.exception.UserNotFountException;
import com.banking.operations.request.dto.CreditRequestDTO;
import com.banking.operations.request.dto.DebitRequestDTO;
import com.banking.operations.request.dto.ViewAccountDTO;
import com.banking.operations.response.dto.UpdatedAccountDetails;
import com.banking.operations.service.BankingOperationsService;
import com.banking.operations.util.ValidationMessages;

@RunWith(MockitoJUnitRunner.class)
public class BankingOperationsComponentTest {

    private BankingOperationsComponent bankingOperationsComponentUnderTest;
    UpdatedAccountDetails updatedAccountDetails;

    @BeforeEach
    public void setUp() {
        bankingOperationsComponentUnderTest = new BankingOperationsComponent();
        bankingOperationsComponentUnderTest.bankingOperationsService = mock(BankingOperationsService.class);
        updatedAccountDetails = new UpdatedAccountDetails();
        updatedAccountDetails.setAccountNumber("123");
        updatedAccountDetails.setAccountType("savings");
        updatedAccountDetails.setBalance(100);
        updatedAccountDetails.setCreationDate(LocalDateTime.now());
        updatedAccountDetails.setDueAmount(0);
    }

    @Test
    public void testCreditAccount() {
        
        CreditRequestDTO creditRequestDTO = new CreditRequestDTO();

        when(bankingOperationsComponentUnderTest.bankingOperationsService.creditAccount(any(CreditRequestDTO.class))).thenReturn(updatedAccountDetails);

        final UpdatedAccountDetails result = bankingOperationsComponentUnderTest.creditAccount(creditRequestDTO);

        assertEquals(result.getAccountNumber(), "123");
        assertEquals(result.getAccountType(), "savings");
        assertNotNull(result.getCreationDate());
        assertTrue(result.getBalance()==100);
        assertTrue(result.getDueAmount()==0);
    }

    @Test
    public void testCreditAccount_BankingOperationsServiceThrowsAccountDoesNotExistsException() {
        
        final CreditRequestDTO creditRequestDTO = new CreditRequestDTO();

        when(bankingOperationsComponentUnderTest.bankingOperationsService.creditAccount(any(CreditRequestDTO.class))).thenThrow(new AccountDoesNotExistsException(ValidationMessages.INVALID_ACCOUNT.getDescription()));

        final UpdatedAccountDetails result = bankingOperationsComponentUnderTest.creditAccount(creditRequestDTO);

        assertNull(result.getAccountNumber());
        assertNull(result.getAccountType());
        assertNull(result.getCreationDate());
        assertNotNull(result.getError());
        assertEquals(result.getError().getErrorCode(),ValidationMessages.INVALID_ACCOUNT.getCode());
        assertEquals(result.getError().getMessage(),ValidationMessages.INVALID_ACCOUNT.getDescription());
    }

    @Test
    public void testCreditAccount_BankingOperationsServiceThrowsUserNotFountException() {
        
        final CreditRequestDTO creditRequestDTO = new CreditRequestDTO();

        when(bankingOperationsComponentUnderTest.bankingOperationsService.creditAccount(any(CreditRequestDTO.class))).thenThrow(new UserNotFountException(ValidationMessages.INVALID_USER.getDescription()));

        final UpdatedAccountDetails result = bankingOperationsComponentUnderTest.creditAccount(creditRequestDTO);

        assertNull(result.getAccountNumber());
        assertNull(result.getAccountType());
        assertNull(result.getCreationDate());
        assertNotNull(result.getError());
        assertEquals(result.getError().getErrorCode(),ValidationMessages.INVALID_USER.getCode());
        assertEquals(result.getError().getMessage(),ValidationMessages.INVALID_USER.getDescription());
    }

    @Test
    public void testDebitAccount() {
        final DebitRequestDTO debitRequestDTO = new DebitRequestDTO();

        when(bankingOperationsComponentUnderTest.bankingOperationsService.debitAccount(any(DebitRequestDTO.class))).thenReturn(updatedAccountDetails);

        final UpdatedAccountDetails result = bankingOperationsComponentUnderTest.debitAccount(debitRequestDTO);

        assertEquals(result.getAccountNumber(), "123");
        assertEquals(result.getAccountType(), "savings");
        assertNotNull(result.getCreationDate());
        assertTrue(result.getBalance()==100);
        assertTrue(result.getDueAmount()==0);
    }

    @Test
    public void testDebitAccount_BankingOperationsServiceThrowsAccountDebitException() {
        
        DebitRequestDTO debitRequestDTO = new DebitRequestDTO();
        debitRequestDTO.setAccountNumber("123");
        debitRequestDTO.setDebitAmount(10000);
        debitRequestDTO.setUsername("username");

        when(bankingOperationsComponentUnderTest.bankingOperationsService.debitAccount(any(DebitRequestDTO.class))).thenThrow(new AccountDebitException(ValidationMessages.LOW_BALANCE.getDescription()));

        final UpdatedAccountDetails result = bankingOperationsComponentUnderTest.debitAccount(debitRequestDTO);

        assertNull(result.getAccountNumber());
        assertNull(result.getAccountType());
        assertNull(result.getCreationDate());
        assertNotNull(result.getError());
        assertEquals(result.getError().getErrorCode(),ValidationMessages.LOW_BALANCE.getCode());
        assertEquals(result.getError().getMessage(),ValidationMessages.LOW_BALANCE.getDescription());
    }

    @Test
    public void testDebitAccount_BankingOperationsServiceThrowsAccountDoesNotExistsException() {
        // Setup
        final DebitRequestDTO debitRequestDTO = new DebitRequestDTO();

        when(bankingOperationsComponentUnderTest.bankingOperationsService.debitAccount(any(DebitRequestDTO.class))).thenThrow(new AccountDoesNotExistsException(ValidationMessages.INVALID_ACCOUNT.getDescription()));

        final UpdatedAccountDetails result = bankingOperationsComponentUnderTest.debitAccount(debitRequestDTO);

        assertNull(result.getAccountNumber());
        assertNull(result.getAccountType());
        assertNull(result.getCreationDate());
        assertNotNull(result.getError());
        assertEquals(result.getError().getErrorCode(),ValidationMessages.INVALID_ACCOUNT.getCode());
        assertEquals(result.getError().getMessage(),ValidationMessages.INVALID_ACCOUNT.getDescription());
    }

    @Test
    public void testDebitAccount_BankingOperationsServiceThrowsUserNotFountException() {
        
        final DebitRequestDTO debitRequestDTO = new DebitRequestDTO();

        when(bankingOperationsComponentUnderTest.bankingOperationsService.debitAccount(any(DebitRequestDTO.class))).thenThrow(new UserNotFountException(ValidationMessages.INVALID_USER.getDescription()));

        final UpdatedAccountDetails result = bankingOperationsComponentUnderTest.debitAccount(debitRequestDTO);

        assertNull(result.getAccountNumber());
        assertNull(result.getAccountType());
        assertNull(result.getCreationDate());
        assertNotNull(result.getError());
        assertEquals(result.getError().getErrorCode(),ValidationMessages.INVALID_USER.getCode());
        assertEquals(result.getError().getMessage(),ValidationMessages.INVALID_USER.getDescription());
    }

    @Test
    public void testGetAccountDetails() {
        
        final ViewAccountDTO viewAccountsDTO = new ViewAccountDTO();

        when(bankingOperationsComponentUnderTest.bankingOperationsService.getAccountDetails(any(ViewAccountDTO.class))).thenReturn(updatedAccountDetails);

        final UpdatedAccountDetails result = bankingOperationsComponentUnderTest.getAccountDetails(viewAccountsDTO);

        assertEquals(result.getAccountNumber(), "123");
        assertEquals(result.getAccountType(), "savings");
        assertNotNull(result.getCreationDate());
        assertTrue(result.getBalance()==100);
        assertTrue(result.getDueAmount()==0);
    }

    @Test
    public void testGetAccountDetails_BankingOperationsServiceThrowsAccountDoesNotExistsException() {
        
        final ViewAccountDTO viewAccountsDTO = new ViewAccountDTO();

        when(bankingOperationsComponentUnderTest.bankingOperationsService.getAccountDetails(any(ViewAccountDTO.class))).thenThrow(new AccountDoesNotExistsException(ValidationMessages.INVALID_ACCOUNT.getDescription()));

        final UpdatedAccountDetails result = bankingOperationsComponentUnderTest.getAccountDetails(viewAccountsDTO);

        assertNull(result.getAccountNumber());
        assertNull(result.getAccountType());
        assertNull(result.getCreationDate());
        assertNotNull(result.getError());
        assertEquals(result.getError().getErrorCode(),ValidationMessages.INVALID_ACCOUNT.getCode());
        assertEquals(result.getError().getMessage(),ValidationMessages.INVALID_ACCOUNT.getDescription());
    }

    @Test
    public void testGetAccountDetails_BankingOperationsServiceThrowsUserNotFountException() {
        
        final ViewAccountDTO viewAccountsDTO = new ViewAccountDTO();

        when(bankingOperationsComponentUnderTest.bankingOperationsService.getAccountDetails(any(ViewAccountDTO.class))).thenThrow(new UserNotFountException(ValidationMessages.INVALID_USER.getDescription()));

        final UpdatedAccountDetails result = bankingOperationsComponentUnderTest.getAccountDetails(viewAccountsDTO);
        
        assertNull(result.getAccountNumber());
        assertNull(result.getAccountType());
        assertNull(result.getCreationDate());
        assertNotNull(result.getError());
        assertEquals(result.getError().getErrorCode(),ValidationMessages.INVALID_USER.getCode());
        assertEquals(result.getError().getMessage(),ValidationMessages.INVALID_USER.getDescription());
    }
}
