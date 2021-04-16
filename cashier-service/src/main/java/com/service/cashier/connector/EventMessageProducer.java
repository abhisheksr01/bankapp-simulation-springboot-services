package com.service.cashier.connector;

import com.service.cashier.model.TransactionVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Slf4j
@Component
public class EventMessageProducer {

    private KafkaTemplate kafkaTemplate;
    private String topicName;

    public EventMessageProducer(KafkaTemplate kafkaTemplate, @Value("${kafka.topicName}") String topicName) {
        this.kafkaTemplate = kafkaTemplate;
        this.topicName = topicName;
    }

    public void produceEventMessage(TransactionVO transactionVO) {
        log.info("EventMessagingConnector:produceEventMessage : Init..");
        ListenableFuture listenableFuture = kafkaTemplate.send(topicName, transactionVO);
        listenableFuture.addCallback(new ListenableFutureCallback<SendResult<String, TransactionVO>>() {

            @Override
            public void onSuccess(SendResult<String, TransactionVO> result) {
                log.debug("EventMessagingConnector:produceEventMessage : Sent message=[{}] with with offset=[{}]",
                        result, result.getRecordMetadata().offset());
            }

            @Override
            public void onFailure(Throwable ex) {
                log.error("EventMessagingConnector:produceEventMessage : Unable to send message : {} due to : {}",
                        transactionVO, ex.getMessage());
                System.out.println("Unable to send message=[" + transactionVO + "] due to : " + ex.getMessage());
            }
        });
        log.info("EventMessagingConnector:produceEventMessage : End..");
    }
}
