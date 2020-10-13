package com.banking.operations.config;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

@RunWith(MockitoJUnitRunner.class)
public class AccountConfigTest {

    private AccountConfig accountConfigUnderTest;

    @BeforeEach
    public void setUp() {
        accountConfigUnderTest = new AccountConfig();
    }

    @Test
    public void testModelMapper() {

        final ModelMapper result = accountConfigUnderTest.modelMapper();

        assertNotNull(result);
    }
}
