package com.service.transaction.messagingevents;

import com.service.transaction.service.TransactionService;
import org.junit.jupiter.api.Test;

import static com.service.transaction.helper.TestData.getCreditTransaction;
import static org.mockito.Mockito.*;

class TransactionEventConsumerTest {

    public static final String TOPIC_NAME = "transaction";

    @Test
    void consumeTransaction_shouldCallService_whenTransactionEventIsReceived() {
        TransactionService mockTransactionService = mock(TransactionService.class);

        TransactionEventConsumer transactionEventConsumer = new TransactionEventConsumer(mockTransactionService);

        transactionEventConsumer.consumeTransaction(getCreditTransaction());

        verify(mockTransactionService, times(1)).processTransaction(getCreditTransaction());
    }
}