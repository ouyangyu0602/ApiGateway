/*
 * Copyright (c) 2008, 2015, OneAPM and/or its affiliates. All rights reserved.
 */
package com.blueocn.api.kong;

import com.blueocn.api.kong.data.CustomerData;
import com.blueocn.api.kong.data.CustomerKeyData;

import java.util.List;

/**
 * Title: CustomerClient
 * Description:
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2015-12-18 10:59
 */
public interface CustomerClient {

    boolean createCustomer(CustomerData customer);

    List<CustomerKeyData> getKeyList(String customerId);

    boolean addKey(String customerId, String key);

    boolean deleteKey(String customerId, String keyId);
}
