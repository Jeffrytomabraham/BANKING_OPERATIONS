package com.banking.operations.component;

import com.banking.operations.exception.AccountDebitException;
import com.banking.operations.request.dto.CreditRequestDTO;
import com.banking.operations.request.dto.DebitRequestDTO;
import com.banking.operations.response.dto.UpdatedAccountDetails;
import com.banking.operations.service.BankingOperationsService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BankingOperationsComponentTest {

    private BankingOperationsComponent bankingOperationsComponentUnderTest;

    @Before
    public void setUp() {
        bankingOperationsComponentUnderTest = new BankingOperationsComponent();
        bankingOperationsComponentUnderTest.bankingOperationsService = mock(BankingOperationsService.class);
    }

    @Test
    public void testCreditAccount() {
        // Setup
        CreditRequestDTO creditRequestDTO = new CreditRequestDTO();
        UpdatedAccountDetails userAccountDetails = new UpdatedAccountDetails();
        userAccountDetails.setAccountNumber("123");
        userAccountDetails.setBalance(100);
        when(bankingOperationsComponentUnderTest.bankingOperationsService.creditAccount(any(CreditRequestDTO.class))).thenReturn(userAccountDetails);

        // Run the test
        final UpdatedAccountDetails result = bankingOperationsComponentUnderTest.creditAccount(creditRequestDTO);

        // Verify the results
        Assert.assertNotNull(result);
    }

    @Test
    public void testDebitAccount() {
        // Setup
        DebitRequestDTO debitRequestDTO = new DebitRequestDTO();

        UpdatedAccountDetails userAccountDetails = new UpdatedAccountDetails();
        userAccountDetails.setAccountNumber("123");
        userAccountDetails.setBalance(100);
        when(bankingOperationsComponentUnderTest.bankingOperationsService.debitAccount(any(DebitRequestDTO.class))).thenReturn(userAccountDetails);

        // Run the test
        final UpdatedAccountDetails result = bankingOperationsComponentUnderTest.debitAccount(debitRequestDTO);

        // Verify the results
        Assert.assertNotNull(result);
    }

    @Test(expected = AccountDebitException.class)
    public void testDebitAccount_BankingOperationsServiceThrowsAccountDebitException() {
        // Setup
        final DebitRequestDTO debitRequestDTO = new DebitRequestDTO();

        when(bankingOperationsComponentUnderTest.bankingOperationsService.debitAccount(any(DebitRequestDTO.class))).thenThrow(AccountDebitException.class);

        // Run the test
        bankingOperationsComponentUnderTest.debitAccount(debitRequestDTO);
    }
}
