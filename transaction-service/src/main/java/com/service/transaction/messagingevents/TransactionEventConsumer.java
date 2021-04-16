package com.service.transaction.messagingevents;

import com.service.transaction.model.TransactionVO;
import com.service.transaction.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TransactionEventConsumer {

    private TransactionService transactionService;

    public TransactionEventConsumer(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @KafkaListener(topics = "transaction", groupId = "group_json")
    public void consumeTransaction(TransactionVO transactionVO) {
        log.info("TransactionEventConsumer:consumeTransaction:Init..");
        this.transactionService.processTransaction(transactionVO);
        log.info("TransactionEventConsumer:consumeTransaction:End..");
    }
}