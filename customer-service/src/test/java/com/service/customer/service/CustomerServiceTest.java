package com.service.customer.service;

import com.service.customer.mapper.CustomerVOToCustomerMapper;
import com.service.customer.mapper.CustomerVOToCustomerMapperImpl;
import com.service.customer.model.Customer;
import com.service.customer.model.CustomerVO;
import com.service.customer.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.HttpClientErrorException;

import static com.service.customer.helper.TestData.getCustomerData;
import static com.service.customer.helper.TestData.getCustomerVOData;
import static java.util.Optional.ofNullable;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class CustomerServiceTest {

    private CustomerRepository mockCustomerRepository = mock(CustomerRepository.class);
    private CustomerService customerService;
    private CustomerVOToCustomerMapper mockCustomerVOToCustomerMapper;
    private CustomerVO customerVOData = getCustomerVOData();
    private Customer customerData = getCustomerData();
    private int CUSTOMER_ID = 1000;

    @BeforeEach
    void setUp() {
        mockCustomerVOToCustomerMapper = mock(CustomerVOToCustomerMapperImpl.class);
        customerService = new CustomerService(mockCustomerVOToCustomerMapper, mockCustomerRepository);
    }

    @Test
    void create_whenPassedCustomerRequestData_shouldCallTheRepositoryWithConvertedDAO() {
        when(mockCustomerVOToCustomerMapper.map(customerVOData)).thenReturn(customerData);

        customerService.create(customerVOData);

        verify(mockCustomerRepository, times(1)).save(customerData);
    }


    @Test
    void create_whenPassedCustomerRequestData_shouldReturnCustomerDataFromRepository() {
        Customer expectedCustomer = new Customer();
        when(mockCustomerVOToCustomerMapper.map(customerVOData)).thenReturn(customerData);
        when(mockCustomerRepository.save(customerData)).thenReturn(expectedCustomer);

        Customer actual = customerService.create(customerVOData);

        assertEquals(expectedCustomer, actual);
    }

    @Test
    void getCustomer_whenCustomerIdPassed_shouldCallTheRepositoryWithSameCustomerId() {
        when(mockCustomerRepository.findById(CUSTOMER_ID)).thenReturn(ofNullable(customerData));

        customerService.getCustomer(CUSTOMER_ID);

        verify(mockCustomerRepository, times(1)).findById(CUSTOMER_ID);
    }

    @Test
    void getCustomer_whenCustomerIdPassed_shouldCallTheRepositoryAndReturnCustomerData() {
        when(mockCustomerRepository.findById(CUSTOMER_ID)).thenReturn(ofNullable(customerData));

        Customer actualCustomer = customerService.getCustomer(CUSTOMER_ID);

        assertEquals(customerData, actualCustomer);
    }

    @Test
    void getCustomer_whenANonExistingCustomerIsPassed_shouldThrowError404() {
        when(mockCustomerRepository.findById(CUSTOMER_ID)).thenReturn(java.util.Optional.empty());

        assertThrows(HttpClientErrorException.class, () -> {
            customerService.getCustomer(CUSTOMER_ID);
        });
    }
}