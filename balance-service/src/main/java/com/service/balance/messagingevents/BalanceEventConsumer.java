package com.service.balance.messagingevents;

import com.service.balance.model.BalanceUpdateVO;
import com.service.balance.service.BalanceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class BalanceEventConsumer {

    private BalanceService balanceService;

    public BalanceEventConsumer(BalanceService balanceService) {
        this.balanceService = balanceService;
    }

    @KafkaListener(topics = "balance", groupId = "group_json")
    public void consumeBalanceUpdateEvent(BalanceUpdateVO balanceUpdateVO) {
        log.info("BalanceEventConsumer:consumeBalanceUpdateEvent:Init..");
        this.balanceService.updateBalance(balanceUpdateVO);
        log.info("BalanceEventConsumer:consumeBalanceUpdateEvent:End..");
    }
}