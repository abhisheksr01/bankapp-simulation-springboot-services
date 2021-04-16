package com.service.customer.helper;

import com.service.customer.model.Customer;
import com.service.customer.model.CustomerVO;

public class TestData {
    public static Customer getCustomerData() {
        Customer customer = new Customer();
        customer.setFirstName("Abhishek");
        customer.setSurname("Rajput");
        return customer;
    }

    public static CustomerVO getCustomerVOData() {
        CustomerVO customer = new CustomerVO();
        customer.setFirstName("Abhishek");
        customer.setSurname("Rajput");
        return customer;
    }
}
