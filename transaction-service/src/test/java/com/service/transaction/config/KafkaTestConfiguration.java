package com.service.transaction.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaTestConfiguration {
    @Bean
    public NewTopic createNewTopics() {
        return new NewTopic("transaction", 1, (short) 1);
    }
}
