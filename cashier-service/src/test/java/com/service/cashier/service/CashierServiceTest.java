package com.service.cashier.service;

import com.service.cashier.connector.AccountConnector;
import com.service.cashier.connector.EventMessageProducer;
import com.service.cashier.model.TransactionVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.service.cashier.helper.TestData.getCreditTransaction;
import static org.mockito.Mockito.*;

class CashierServiceTest {

    private CashierService cashierService;
    private AccountConnector mockAccountConnector;
    private EventMessageProducer mockEventMessageProducer;

    private

    @BeforeEach
    void setUp() {
        mockAccountConnector = mock(AccountConnector.class);
        mockEventMessageProducer = mock(EventMessageProducer.class);
        cashierService = new CashierService(mockAccountConnector, mockEventMessageProducer);
    }

    @Test
    void create_whenTransactionDataIsPassed_shouldVerifyCallToAccountService_thenVerifyCallToEventProducer() {
        TransactionVO transaction = getCreditTransaction();

        cashierService.create(transaction);

        verify(mockAccountConnector, times(1)).
                invokeAccountService(transaction.getAccountNumber());
        verify(mockEventMessageProducer, times(1)).
                produceEventMessage(transaction);
    }
}