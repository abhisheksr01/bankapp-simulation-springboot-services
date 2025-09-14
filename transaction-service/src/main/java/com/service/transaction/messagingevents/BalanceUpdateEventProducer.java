package com.service.transaction.messagingevents;

import com.service.transaction.model.BalanceUpdateVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Component
public class BalanceUpdateEventProducer {

    private KafkaTemplate<String, BalanceUpdateVO> kafkaTemplate;
    private String topicName;

    public BalanceUpdateEventProducer(KafkaTemplate kafkaTemplate, @Value("${kafka.topicName}") String topicName) {
        this.kafkaTemplate = kafkaTemplate;
        this.topicName = topicName;
    }

    public void publishUpdateBalance(BalanceUpdateVO balanceUpdateVO) {
        log.info("BalanceUpdateEventProducer:produceBalanceUpdate : Init..");
        CompletableFuture<SendResult<String, BalanceUpdateVO>> listenableFuture = kafkaTemplate.send(topicName, balanceUpdateVO);
        log.debug("BalanceUpdateEventProducer:produceBalanceUpdate listenableFuture : {}", listenableFuture);
        log.info("BalanceUpdateEventProducer:produceBalanceUpdate : End..");
    }
}
