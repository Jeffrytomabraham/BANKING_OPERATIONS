package com.banking.operations.request.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ViewAccountDTOTest {

    private ViewAccountDTO viewAccountDTOUnderTest;

    @BeforeEach
    public void setUp() {
        viewAccountDTOUnderTest = new ViewAccountDTO();
    }
    
    @Test
    public void setViewAccounts() {
    	viewAccountDTOUnderTest.setAccountNumber("123");
    	viewAccountDTOUnderTest.setUsername("user");
    	
    	assertEquals(viewAccountDTOUnderTest.getAccountNumber(), "123");
        assertEquals(viewAccountDTOUnderTest.getUsername(), "user");
    }
    
}
