package com.service.balance.service;

import com.service.balance.model.BalanceUpdateVO;
import com.service.balance.repository.BalanceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

import static com.service.balance.helper.TestData.*;
import static java.util.Optional.ofNullable;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class BalanceServiceTest {

    private BalanceService balanceService;
    private BalanceRepository mockBalanceRepository;

    @BeforeEach
    void setUp() {
        mockBalanceRepository = mock(BalanceRepository.class);
        balanceService = new BalanceService(mockBalanceRepository);
    }

    @Test
    void updateBalance_shouldUpdateTheBalance_whenBalanceUpdateVOIsProvided() {
        when(mockBalanceRepository.save(getBalanceUpdateDAO())).thenReturn(getBalanceUpdateDAO());

        balanceService.updateBalance(getBalanceUpdateVO());

        verify(mockBalanceRepository, times(1)).save(getBalanceUpdateDAO());
    }

    @Test
    void getBalance_shouldReturnBalance_whenAccountNumberIsProvided() {
        when(mockBalanceRepository.findById(ACCOUNT_NUMBER)).thenReturn(ofNullable(getBalanceUpdateDAO()));

        BalanceUpdateVO balanceUpdateVO = balanceService.getCurrentBalance(ACCOUNT_NUMBER);

        verify(mockBalanceRepository, times(1)).findById(ACCOUNT_NUMBER);
        assertEquals(getBalanceUpdateVO(), balanceUpdateVO);
    }

    @Test
    void getAccountDetails_whenANonExistingAccountIsPassed_shouldThrowError404() {
        when(mockBalanceRepository.findById(ACCOUNT_NUMBER)).thenReturn(java.util.Optional.empty());

        HttpClientErrorException exception = assertThrows(HttpClientErrorException.class, () -> {
            balanceService.getCurrentBalance(ACCOUNT_NUMBER);
        });
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
        assertEquals("Account Number not found", exception.getStatusText());
    }
}