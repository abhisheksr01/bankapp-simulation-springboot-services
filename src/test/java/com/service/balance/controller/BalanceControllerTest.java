package com.service.balance.controller;

import com.service.balance.helper.TestData;
import com.service.balance.model.BalanceUpdateVO;
import com.service.balance.service.BalanceService;
import org.junit.jupiter.api.Test;

import static com.service.balance.helper.TestData.ACCOUNT_NUMBER;
import static com.service.balance.helper.TestData.getBalanceUpdateVO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class BalanceControllerTest {

    BalanceService mockBalanceService = mock(BalanceService.class);
    BalanceController balanceController = new BalanceController(mockBalanceService);

    @Test
    void getCurrentBalance_whenAccountNumberIsPassed_shouldReturnBalanceVO() {
        when(mockBalanceService.getCurrentBalance(ACCOUNT_NUMBER)).thenReturn(getBalanceUpdateVO());

        BalanceUpdateVO actualBalanceUpdateVO = balanceController.getCurrentBalance(ACCOUNT_NUMBER);

        assertEquals(TestData.getBalanceUpdateVO(), actualBalanceUpdateVO);
    }
}