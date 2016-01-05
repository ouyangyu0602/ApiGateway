package com.blueocn.api.kong;

import com.blueocn.api.MockTest;
import com.blueocn.api.kong.data.CustomerData;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class CustomerClientTest extends MockTest {

    @Mock
    private CustomerClient customerClient;

    @Mock
    private CustomerData customerData;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        Mockito.when(customerClient.createCustomer(customerData)).thenReturn(true);
        Mockito.when(customerData.getCustomerId()).thenReturn("Test Mock");
    }

    @Test
    public void testCreateCustomer() throws Exception {
        Assert.assertTrue(customerClient.createCustomer(customerData));
        Assert.assertTrue("Test Mock".equals(customerData.getCustomerId()));
    }
}
