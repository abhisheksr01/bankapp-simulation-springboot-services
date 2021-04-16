package com.service.account.controller;

import com.service.account.helper.TestData;
import com.service.account.model.AccountCustomerVO;
import com.service.account.model.AccountVO;
import com.service.account.model.Customer;
import com.service.account.service.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.service.account.helper.TestData.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AccountControllerTest {

    private AccountController accountController;
    private AccountService mockAccountService;
    private AccountVO mockCustomer;
    private Customer customerData = getCustomerData();

    @BeforeEach
    void setUp() {
        mockAccountService = mock(AccountService.class);
        accountController = new AccountController(mockAccountService);
    }

    @Test
    void create_whenAValidCustomerIdIsPassed_shouldCallTheServiceWithTheSameId() {
        accountController.create(CUSTOMER_ID);

        verify(mockAccountService, times(1)).create(CUSTOMER_ID);
    }

    @Test
    void create_whenAValidCustomerIdIsPassed_shouldReturnAccountDetailsWithCustomerData() {
        AccountVO accountsVO = getAccountVO();
        when(mockAccountService.create(CUSTOMER_ID)).thenReturn(accountsVO);

        AccountVO actualAccountVO = accountController.create(CUSTOMER_ID);

        assertEquals(accountsVO, actualAccountVO);
    }

    @Test
    void getAccountDetails_whenAValidAccountNumberIsPassed_shouldCallServiceWithSameAccountNumber() {
        accountController.getAccountDetails(ACCOUNT_NUMBER);

        verify(mockAccountService, times(1)).getAccountDetails(ACCOUNT_NUMBER);
    }

    @Test
    void getAccountDetails_whenAValidAccountNumberIsPassed_shouldReturnResponseFromService() {
        when(mockAccountService.getAccountDetails(ACCOUNT_NUMBER)).thenReturn(TestData.getAccountCustomerVO());

        AccountCustomerVO actualAccountResponse = accountController.getAccountDetails(ACCOUNT_NUMBER);

        assertEquals(getAccountCustomerVO(), actualAccountResponse);
    }
}