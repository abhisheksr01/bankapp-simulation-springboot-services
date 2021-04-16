package com.service.customer.controller;

import com.service.customer.model.Customer;
import com.service.customer.model.CustomerVO;
import com.service.customer.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static com.service.customer.helper.TestData.getCustomerData;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class CustomerControllerTest {

    private CustomerController customerController;
    private CustomerService mockCustomerService;
    private CustomerVO mockCustomer;
    private Customer customerData = getCustomerData();
    private int CUSTOMER_ID = 1000;

    @BeforeEach
    void setUp() {
        mockCustomerService = mock(CustomerService.class);
        mockCustomer = mock(CustomerVO.class);
        customerController = new CustomerController(mockCustomerService);
    }

    @Test
    void create_whenCustomerDataIsPassed_shouldCallServiceWithGivenInput() {
        customerController.create(mockCustomer);

        verify(mockCustomerService, times(1)).create(mockCustomer);
    }

    @Test
    void create_whenCustomerDataIsPassed_shouldReturnTheCustomerData_returnedByTheService() {
        when(mockCustomerService.create(mockCustomer)).thenReturn(customerData);

        Customer actualCustomerData = customerController.create(mockCustomer);

        assertEquals(customerData, actualCustomerData);
    }

    @Test
    void getCustomer_whenCustomerIdsPassed_shouldCallServiceWithGivenInput() {
        customerController.getCustomer(CUSTOMER_ID);

        verify(mockCustomerService, times(1)).getCustomer(CUSTOMER_ID);
    }

    @Test
    void getCustomer_whenAValidCustomerIdIsPassed_shouldReturnCustomerData() {
        when(mockCustomerService.getCustomer(CUSTOMER_ID)).thenReturn(customerData);

        Customer actualCustomer = customerController.getCustomer(CUSTOMER_ID);

        assertEquals(customerData, actualCustomer);
    }

    @Test
    void create_whenInvalidCustomerDataIsPassed_shouldReturnConstraintViolationError() {
        CustomerVO customerVO = new CustomerVO();
        customerVO.setFirstName("@bhi$h€ksr01");
        customerVO.setSurname("r@jput|");

        List<String> errors = getConstraintErrors(customerVO);

        assertTrue(errors.contains("Invalid name"));
        assertTrue(errors.contains("Invalid surname"));

    }

    @Test
    void create_whenNullPassedInCustomerData_shouldReturnConstraintViolationError() {
        CustomerVO customerVO = new CustomerVO();
        customerVO.setFirstName(null);
        customerVO.setSurname(null);

        List<String> errors = getConstraintErrors(customerVO);

        assertTrue(errors.contains("Name cannot be blank"));
        assertTrue(errors.contains("Surname cannot be blank"));

    }

    private List<String> getConstraintErrors(CustomerVO customerVO) {
        List<String> errors = new ArrayList<>();
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Set<ConstraintViolation<CustomerVO>> constraintViolations = factory.getValidator().validate(customerVO);
        constraintViolations.forEach(constraintViolation -> errors.add(constraintViolation.getMessage()));
        return errors;
    }
}