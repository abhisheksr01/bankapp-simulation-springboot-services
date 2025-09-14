package com.service.cashier.config;

import org.junit.jupiter.api.Test;
import org.springframework.kafka.core.KafkaAdmin;

import static org.apache.kafka.clients.admin.AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CashierConfigurationTest {

    private CashierConfiguration cashierConfiguration =
            new CashierConfiguration("mock-address", "mock-topic");

    @Test
    void kafkaAdmin_shouldSetTheSameBootStrapAddress() {
        KafkaAdmin kafkaAdmin = cashierConfiguration.kafkaAdmin();

        Object actual = null;
        try {
            var field = KafkaAdmin.class.getDeclaredField("configs");
            field.setAccessible(true);
            actual = ((java.util.Map<?, ?>) field.get(kafkaAdmin)).get(BOOTSTRAP_SERVERS_CONFIG);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void createNewTopic_shouldSetTheSameTopicName() {
        assertEquals("mock-topic", cashierConfiguration.createNewTopic().name());
    }
}
