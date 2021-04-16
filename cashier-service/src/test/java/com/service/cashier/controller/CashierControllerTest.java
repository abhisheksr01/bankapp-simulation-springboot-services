package com.service.cashier.controller;

import com.service.cashier.model.TransactionVO;
import com.service.cashier.service.CashierService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.service.cashier.helper.TestData.getCreditTransaction;
import static org.mockito.Mockito.*;

class CashierControllerTest {
    private CashierController cashierController;
    private CashierService mockCashierService;

    @BeforeEach
    void setUp() {
        mockCashierService = mock(CashierService.class);
        cashierController = new CashierController(mockCashierService);
    }

    @Test
    void create_whenAValidTransactionIsPassed_shouldCallTheService() {
        TransactionVO creditTransaction = getCreditTransaction();

        cashierController.create(creditTransaction);

        verify(mockCashierService, times(1)).create(creditTransaction);
    }
}