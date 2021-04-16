package com.service.account.service;

import com.service.account.connector.CustomerConnector;
import com.service.account.helper.TestData;
import com.service.account.model.AccountCustomerVO;
import com.service.account.model.AccountVO;
import com.service.account.model.Customer;
import com.service.account.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Optional;

import static com.service.account.helper.TestData.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class AccountServiceTest {

    private AccountService accountService;
    private CustomerConnector mockCustomerConnector;
    private AccountRepository mockAccountRepository;

    @BeforeEach
    void setUp() {
        mockCustomerConnector = mock(CustomerConnector.class);
        mockAccountRepository = mock(AccountRepository.class);
        accountService = new AccountService(mockCustomerConnector, mockAccountRepository);
    }

    @Test
    void create_whenAValidCustomerIdIsPassed_shouldCallTheConnectorWithTheSameCustomerId() {
        accountService.create(CUSTOMER_ID);

        verify(mockCustomerConnector, times(1)).getCustomer(CUSTOMER_ID);
    }

    @Test
    void create_whenAValidCustomerIdIsPassed_shouldCallTheCustomerRepositoryWithTheCustomerDetails() {
        when(mockCustomerConnector.getCustomer(CUSTOMER_ID)).thenReturn(getCustomerData());

        accountService.create(CUSTOMER_ID);

        verify(mockAccountRepository, times(1)).save(getRequestAccountDAO());
    }

    @Test
    void create_whenAValidCustomerIdIsPassed_shouldCreateAAccountAndReturnTheAccountDetails() {
        Customer customerData = getCustomerData();
        when(mockCustomerConnector.getCustomer(CUSTOMER_ID)).thenReturn(customerData);
        when(mockAccountRepository.save(TestData.getRequestAccountDAO())).thenReturn(getStoredAccountDAO());

        AccountVO accountVO = accountService.create(CUSTOMER_ID);

        assertEquals(customerData.getId(), accountVO.getCustomerId());
        assertEquals(getStoredAccountDAO().getAccountNumber(), accountVO.getAccountNumber());
        assertEquals(customerData.getFirstName(), accountVO.getFirstName());
        assertEquals(customerData.getSurname(), accountVO.getSurname());
    }

    @Test
    void getAccountDetails_whenAValidAccountNumberIsPasses_shouldReturnAccountDetails() {
        when(mockAccountRepository.findById(ACCOUNT_NUMBER)).thenReturn(Optional.of(TestData.getStoredAccountDAO()));

        AccountCustomerVO actualAccountsData = accountService.getAccountDetails(ACCOUNT_NUMBER);

        assertEquals(getAccountCustomerVO(), actualAccountsData);
    }

    @Test
    void getAccountDetails_whenANonExistingAccountIsPassed_shouldThrowError404() {
        when(mockAccountRepository.findById(ACCOUNT_NUMBER)).thenReturn(java.util.Optional.empty());

        HttpClientErrorException exception = assertThrows(HttpClientErrorException.class, () -> {
            accountService.getAccountDetails(ACCOUNT_NUMBER);
        });
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
        assertEquals("Account Number not found", exception.getStatusText());
    }
}