package com.banking.operations.entity;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class OperationsEntityTest {

    private OperationsEntity operationsEntityUnderTest;

    @Before
    public void setUp() {
        operationsEntityUnderTest = new OperationsEntity();
    }

    @Test
    public void setOperationsEntity(){
        operationsEntityUnderTest.setAddress1("Addr");
        operationsEntityUnderTest.setAddress2("Addr");
        operationsEntityUnderTest.setAge(10);
        operationsEntityUnderTest.setCity("tvm");
        operationsEntityUnderTest.setCountry("india");
        operationsEntityUnderTest.setDob("02-02-2020");
        operationsEntityUnderTest.setEmail("abc@abc.com");
        operationsEntityUnderTest.setFirstName("firstName");
        operationsEntityUnderTest.setId("111");
        operationsEntityUnderTest.setLastName("lastName");
        operationsEntityUnderTest.setPassword("pass");
        operationsEntityUnderTest.setPhone(1234);
        operationsEntityUnderTest.setPostalCode("postal");
        
        Assert.assertEquals(operationsEntityUnderTest.getAddress1(), "Addr");
        Assert.assertEquals(operationsEntityUnderTest.getAddress2(), "Addr");
        Assert.assertTrue(operationsEntityUnderTest.getAge() == 10);
        Assert.assertEquals(operationsEntityUnderTest.getCity(), "tvm");
        Assert.assertEquals(operationsEntityUnderTest.getDob(),"02-02-2020");
        Assert.assertEquals(operationsEntityUnderTest.getCountry(),"india");
        Assert.assertEquals(operationsEntityUnderTest.getEmail(),"abc@abc.com");
        Assert.assertEquals(operationsEntityUnderTest.getFirstName(),"firstName");
        Assert.assertEquals(operationsEntityUnderTest.getId(),"111");
        Assert.assertEquals(operationsEntityUnderTest.getLastName(),"lastName");
        Assert.assertEquals(operationsEntityUnderTest.getPassword(),"pass");
        Assert.assertEquals(operationsEntityUnderTest.getPhone(),1234);
        Assert.assertEquals(operationsEntityUnderTest.getPostalCode(),"postal");
    }

}
