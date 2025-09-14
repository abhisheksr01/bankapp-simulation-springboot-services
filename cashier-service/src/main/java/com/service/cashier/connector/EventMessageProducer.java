package com.service.cashier.connector;

import com.service.cashier.model.TransactionVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EventMessageProducer {

    private final KafkaTemplate<String, TransactionVO> kafkaTemplate;
    private final String topicName;

    public EventMessageProducer(KafkaTemplate<String, TransactionVO> kafkaTemplate, @Value("${kafka.topicName}") String topicName) {
        this.kafkaTemplate = kafkaTemplate;
        this.topicName = topicName;
    }

    public void produceEventMessage(TransactionVO transactionVO) {
        log.info("EventMessagingConnector:produceEventMessage : Init..");
        kafkaTemplate.send(topicName, transactionVO)
                .thenAccept(result -> log.debug("EventMessagingConnector:produceEventMessage : Sent message=[{}] with offset=[{}]",
                        result, result.getRecordMetadata().offset()))
                .exceptionally(ex -> {
                    log.error("EventMessagingConnector:produceEventMessage : Unable to send message : {} due to : {}",
                            transactionVO, ex.getMessage());
                    System.out.println("Unable to send message=[" + transactionVO + "] due to : " + ex.getMessage());
                    return null;
                });
        log.info("EventMessagingConnector:produceEventMessage : End..");
    }
}
