package com.service.transaction.service;

import com.service.transaction.helper.TestData;
import com.service.transaction.messagingevents.BalanceUpdateEventProducer;
import com.service.transaction.model.TransactionDAO;
import com.service.transaction.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.service.transaction.helper.TestData.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class TransactionServiceTest {

    private TransactionService transactionService;
    private TransactionRepository mockTransactionRepository;
    private BalanceUpdateEventProducer mockBalanceUpdateEventProducer;
    private List<TransactionDAO> transactions = new ArrayList<>();

    @BeforeEach
    void setUp() {
        mockTransactionRepository = mock(TransactionRepository.class);
        mockBalanceUpdateEventProducer = mock(BalanceUpdateEventProducer.class);
        transactionService = new TransactionService(mockTransactionRepository, mockBalanceUpdateEventProducer);
        transactions.add(getCreditTransactionDAO());
        transactions.add(getCreditTransactionDAO());
    }

    @Test
    void processTransaction_whenTransactionTypeCreditIsPassed_shouldGetCurrentBalanceAndUpdateNewBalance() {
        when(mockTransactionRepository.findTransactionsByAccountNumber(getCreditTransaction().getAccountNumber())).
                thenReturn(transactions);

        double actualBalance = transactionService.processTransaction(getCreditTransaction());

        assertEquals(150, actualBalance);
    }

    @Test
    void processTransaction_whenTransactionTypeDebitIsPassed_shouldGetCurrentBalance() {
        transactions.add(getCreditTransactionDAO());
        when(mockTransactionRepository.findTransactionsByAccountNumber(getCreditTransaction().getAccountNumber())).
                thenReturn(transactions);

        double actualBalance = transactionService.processTransaction(getDebitTransaction());

        assertEquals(130, actualBalance);
    }

    @Test
    void processTransaction_whenNewBalanceIsCalculatedSuccessfully_shouldProduceBalanceUpdateEvent() {
        when(mockTransactionRepository.findTransactionsByAccountNumber(getCreditTransaction().getAccountNumber())).
                thenReturn(transactions);

        double actualBalance = transactionService.processTransaction(getDebitTransaction());

        verify(mockBalanceUpdateEventProducer, times(1)).
                publishUpdateBalance(getBalanceUpdateEventMessage());
    }

    @Test
    void processTransaction_whenNewBalanceIsPublished_shouldStoreTheTransactionDetailsInTransactionRepo() {
        when(mockTransactionRepository.findTransactionsByAccountNumber(getCreditTransaction().getAccountNumber())).
                thenReturn(transactions);

        double actualBalance = transactionService.processTransaction(getCreditTransaction());

        verify(mockTransactionRepository, times(1)).save(TestData.getCreditTransactionDAO());
    }
}