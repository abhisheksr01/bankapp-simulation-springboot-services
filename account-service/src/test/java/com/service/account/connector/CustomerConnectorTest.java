package com.service.account.connector;

import com.service.account.model.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static com.service.account.helper.TestData.CUSTOMER_ID;
import static com.service.account.helper.TestData.getCustomerData;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class CustomerConnectorTest {

    private RestTemplate mockRestTemplate;
    private CustomerConnector customerConnector;
    private String GET_CUSTOMER_ENDPOINT = "http://localhost:8081/customer/search/{customerId}";
    private ResponseEntity<Customer> mockResponseEntity;

    @BeforeEach
    void setUp() {
        mockRestTemplate = mock(RestTemplate.class);
        mockResponseEntity = mock(ResponseEntity.class);
        customerConnector = new CustomerConnector(GET_CUSTOMER_ENDPOINT, mockRestTemplate);
    }

    @Test
    void getCustomer_whenValidCustomerIdIsPassed_shouldCallRestTemplateGETWithSameCustomerId() {
        when(mockRestTemplate.getForEntity(GET_CUSTOMER_ENDPOINT, Customer.class, CUSTOMER_ID)).thenReturn(mockResponseEntity);
        when(mockResponseEntity.getBody()).thenReturn(getCustomerData());

        customerConnector.getCustomer(CUSTOMER_ID);

        verify(mockRestTemplate, times(1)).getForEntity(GET_CUSTOMER_ENDPOINT, Customer.class, CUSTOMER_ID);
    }

    @Test
    void getCustomer_whenValidCustomerIdIsPassed_shouldReturnCustomerDetails() {
        when(mockRestTemplate.getForEntity(GET_CUSTOMER_ENDPOINT, Customer.class, CUSTOMER_ID)).thenReturn(mockResponseEntity);
        when(mockResponseEntity.getBody()).thenReturn(getCustomerData());

        Customer actualCustomerData = customerConnector.getCustomer(CUSTOMER_ID);

        assertEquals(getCustomerData(), actualCustomerData);
    }

    @Test
    void getCustomer_whenANonExistingCustomerIsPassed_shouldThrowError404() {
        when(mockRestTemplate.getForEntity(GET_CUSTOMER_ENDPOINT, Customer.class, CUSTOMER_ID))
                .thenThrow(new HttpClientErrorException(HttpStatus.NOT_FOUND));

        HttpClientErrorException exception = assertThrows(HttpClientErrorException.class, () -> {
            customerConnector.getCustomer(CUSTOMER_ID);
        });
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
        assertEquals("Customer not found", exception.getStatusText());
    }

    @Test
    void getCustomer_whenCustomerServiceIsNotAvailable_shouldThrowInternalServerError() {
        when(mockRestTemplate.getForEntity(GET_CUSTOMER_ENDPOINT, Customer.class, CUSTOMER_ID))
                .thenThrow(new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR));

        HttpClientErrorException exception = assertThrows(HttpClientErrorException.class, () -> {
            customerConnector.getCustomer(CUSTOMER_ID);
        });
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, exception.getStatusCode());
        assertEquals("Internal Server Error", exception.getStatusText());
    }
}