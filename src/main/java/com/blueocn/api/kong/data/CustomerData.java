package com.blueocn.api.kong.data;

import java.io.Serializable;

/**
 * Title: CustomerData
 * Description:
 *
 * @author Yufan
 * @version 1.0.0
 * @since 2015-12-18 11:02
 */
public class CustomerData implements Serializable {

    private static final long serialVersionUID = 939842607769177935L;

    private String id;

    private String customerId;

    private String customerName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}
