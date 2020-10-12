package com.banking.operations.entity;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.util.Arrays;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UserDetailsEntityDTOTest {

    private UserDetailsEntityDTO userDetailsEntityDTOUnderTest;
    
    List<AccountsDTO> accountList = new ArrayList<>();
    
    @Before
    public void setUp() {
        userDetailsEntityDTOUnderTest = new UserDetailsEntityDTO();
        AccountsDTO account = new AccountsDTO();
        account.setAccountNumber("123");
        account.setAccountType("SAVINGS");
        account.setBalance(1000);
       
        accountList.add(account);
    }

    @Test
    public void setUserDetails(){
        userDetailsEntityDTOUnderTest.setAccounts(accountList);
        userDetailsEntityDTOUnderTest.setAddress1("Addr");
        userDetailsEntityDTOUnderTest.setAddress2("Addr");
        userDetailsEntityDTOUnderTest.setAge(10);
        userDetailsEntityDTOUnderTest.setCity("tvm");
        userDetailsEntityDTOUnderTest.setCountry("india");
        userDetailsEntityDTOUnderTest.setDob("02-02-2020");
        userDetailsEntityDTOUnderTest.setEmail("abc@abc.com");
        userDetailsEntityDTOUnderTest.setFirstName("firstName");
        userDetailsEntityDTOUnderTest.setId("111");
        userDetailsEntityDTOUnderTest.setLastName("lastName");
        userDetailsEntityDTOUnderTest.setPassword("pass");
        userDetailsEntityDTOUnderTest.setPhone(1234);
        userDetailsEntityDTOUnderTest.setPostalCode("postal");
        userDetailsEntityDTOUnderTest.setUserName("username");
        
        Assert.assertEquals(userDetailsEntityDTOUnderTest.getAddress1(), "Addr");
        Assert.assertEquals(userDetailsEntityDTOUnderTest.getAddress2(), "Addr");
        Assert.assertTrue(userDetailsEntityDTOUnderTest.getAge() == 10);
        Assert.assertEquals(userDetailsEntityDTOUnderTest.getCity(), "tvm");
        Assert.assertEquals(userDetailsEntityDTOUnderTest.getDob(),"02-02-2020");
        Assert.assertEquals(userDetailsEntityDTOUnderTest.getCountry(),"india");
        Assert.assertEquals(userDetailsEntityDTOUnderTest.getEmail(),"abc@abc.com");
        Assert.assertEquals(userDetailsEntityDTOUnderTest.getFirstName(),"firstName");
        Assert.assertEquals(userDetailsEntityDTOUnderTest.getId(),"111");
        Assert.assertEquals(userDetailsEntityDTOUnderTest.getLastName(),"lastName");
        Assert.assertEquals(userDetailsEntityDTOUnderTest.getPassword(),"pass");
        Assert.assertEquals(userDetailsEntityDTOUnderTest.getPhone(),1234);
        Assert.assertEquals(userDetailsEntityDTOUnderTest.getPostalCode(),"postal");
        Assert.assertTrue(userDetailsEntityDTOUnderTest.getAccounts().size()>0);
        Assert.assertEquals(userDetailsEntityDTOUnderTest.getUserName(),"username");
    }
}
