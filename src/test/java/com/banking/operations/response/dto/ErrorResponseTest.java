package com.banking.operations.response.dto;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ErrorResponseTest {

    private ErrorResponse errorResponseUnderTest;

    @BeforeEach
    public void setUp() {
        errorResponseUnderTest = new ErrorResponse();
    }

    @Test
    public void setErrorResponse(){

    	errorResponseUnderTest.setErrorCode("code");
    	errorResponseUnderTest.setMessage("message");
    	
    	Assert.assertEquals(errorResponseUnderTest.getErrorCode(), "code");
        Assert.assertEquals(errorResponseUnderTest.getMessage(), "message");
    }
}
