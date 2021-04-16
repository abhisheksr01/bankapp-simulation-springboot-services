package com.service.customer.mapper;

import com.service.customer.model.Customer;
import com.service.customer.model.CustomerVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class CustomerVOToCustomerMapperTest {

    private CustomerVOToCustomerMapper customerVOToCustomerMapper;

    @BeforeEach
    void setUp() {
        customerVOToCustomerMapper = new CustomerVOToCustomerMapperImpl();
    }

    @Test
    void map_whenCustomerIsPassed_shouldMapToCustomerDAO() {
        CustomerVO customer = new CustomerVO();
        customer.setFirstName("Abhishek");
        customer.setSurname("Rajput");

        Customer customerDAO = customerVOToCustomerMapper.map(customer);

        assertEquals("Abhishek", customerDAO.getFirstName());
        assertEquals("Rajput", customerDAO.getSurname());
    }

    @Test
    void map_whenCustomerPassesIsNull_shouldReturnNull() {
        assertNull(customerVOToCustomerMapper.map(null));
    }
}