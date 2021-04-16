package com.service.balance.config;

import org.junit.jupiter.api.Test;
import org.springframework.kafka.core.KafkaAdmin;

import static org.apache.kafka.clients.admin.AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BalanceConfigurationTest {

    private BalanceConfiguration balanceConfiguration =
            new BalanceConfiguration("mock-address", "mock-topic");

    @Test
    void kafkaAdmin_shouldSetTheSameBootStrapAddress() {
        KafkaAdmin kafkaAdmin = balanceConfiguration.kafkaAdmin();

        Object actual = kafkaAdmin.getConfig().get(BOOTSTRAP_SERVERS_CONFIG);

        assertEquals("mock-address", actual);
    }

    @Test
    void createNewTopic_shouldSetTheSameTopicName() {
        assertEquals("mock-topic", balanceConfiguration.createNewTopic().name());
    }
}