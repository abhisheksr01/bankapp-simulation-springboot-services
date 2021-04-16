package com.service.cashier.service;

import com.service.cashier.connector.AccountConnector;
import com.service.cashier.connector.EventMessageProducer;
import com.service.cashier.model.TransactionVO;
import org.springframework.stereotype.Service;

@Service
public class CashierService {

    private AccountConnector accountConnector;
    private EventMessageProducer eventMessageProducer;

    public CashierService(AccountConnector accountConnector, EventMessageProducer eventMessageProducer) {
        this.accountConnector = accountConnector;
        this.eventMessageProducer = eventMessageProducer;
    }

    public void create(TransactionVO transaction) {
        this.accountConnector.invokeAccountService(transaction.getAccountNumber());
        this.eventMessageProducer.produceEventMessage(transaction);
    }
}
