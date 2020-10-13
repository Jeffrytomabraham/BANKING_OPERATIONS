package com.banking.operations.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.banking.operations.component.BankingOperationsComponent;
import com.banking.operations.request.dto.CreditRequestDTO;
import com.banking.operations.request.dto.DebitRequestDTO;
import com.banking.operations.request.dto.ViewAccountDTO;
import com.banking.operations.response.dto.UpdatedAccountDetails;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(MockitoJUnitRunner.Silent.class)
public class BankingOperationsControllerTest {

    private BankingOperationsController bankingOperationsControllerUnderTest;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        bankingOperationsControllerUnderTest = new BankingOperationsController();
        bankingOperationsControllerUnderTest.bankingOperationsComponent = mock(BankingOperationsComponent.class);
        mockMvc= MockMvcBuilders.standaloneSetup(bankingOperationsControllerUnderTest).build();
    }

    @Test
    public void testCreditOperation() throws Exception{
        // Setup
    	
        CreditRequestDTO creditRequestDTO = new CreditRequestDTO();
        UpdatedAccountDetails updatedAccountDetails= new UpdatedAccountDetails();
        updatedAccountDetails.setAccountNumber("123");
        updatedAccountDetails.setBalance(200);
        updatedAccountDetails.setError(null);
        when(bankingOperationsControllerUnderTest.bankingOperationsComponent.creditAccount(any(CreditRequestDTO.class))).thenReturn(updatedAccountDetails);

        MvcResult result = mockMvc.perform( MockMvcRequestBuilders
        	      .post("/banking/operations/credit")
        	      .content(asJsonString(creditRequestDTO))
        	      .contentType(MediaType.APPLICATION_JSON)
        	      .accept(MediaType.APPLICATION_JSON)).andReturn();
        String content = result.getResponse().getContentAsString();
        Assert.assertEquals(content,asJsonString(updatedAccountDetails));

        // Verify the results
    }

    @Test
    public void testDebitOperation() throws Exception{
        // Setup
        final DebitRequestDTO debitRequestDTO = new DebitRequestDTO();

        when(bankingOperationsControllerUnderTest.bankingOperationsComponent.debitAccount(any(DebitRequestDTO.class))).thenReturn(new UpdatedAccountDetails());

        // Run the test
        UpdatedAccountDetails updatedAccountDetails= new UpdatedAccountDetails();
        updatedAccountDetails.setAccountNumber("123");
        updatedAccountDetails.setBalance(200);
        updatedAccountDetails.setError(null);
        when(bankingOperationsControllerUnderTest.bankingOperationsComponent.debitAccount(any(DebitRequestDTO.class))).thenReturn(updatedAccountDetails);

        MvcResult result = mockMvc.perform( MockMvcRequestBuilders
                .post("/banking/operations/debit")
                .content(asJsonString(debitRequestDTO))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andReturn();
        String content = result.getResponse().getContentAsString();
        Assert.assertEquals(content,asJsonString(updatedAccountDetails));

    }
    
    @Test
    public void testViewAccountDetails() throws Exception{
        // Setup
        final DebitRequestDTO debitRequestDTO = new DebitRequestDTO();

        // Run the test
        UpdatedAccountDetails updatedAccountDetails= new UpdatedAccountDetails();
        updatedAccountDetails.setAccountNumber("123");
        updatedAccountDetails.setBalance(200);
        updatedAccountDetails.setError(null);
        when(bankingOperationsControllerUnderTest.bankingOperationsComponent.getAccountDetails(any(ViewAccountDTO.class))).thenReturn(updatedAccountDetails);

        MvcResult result = mockMvc.perform( MockMvcRequestBuilders
                .post("/banking/operations/account/details")
                .content(asJsonString(debitRequestDTO))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andReturn();
        String content = result.getResponse().getContentAsString();
        Assert.assertEquals(content,asJsonString(updatedAccountDetails));

        // Verify the results
    }

    
    
    public  String asJsonString( Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
