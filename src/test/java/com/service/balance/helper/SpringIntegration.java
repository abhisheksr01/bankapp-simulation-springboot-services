package com.service.balance.helper;

import com.service.balance.BalanceApplication;
import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;

@SpringBootTest(
        classes = BalanceApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
@EmbeddedKafka
@AutoConfigureEmbeddedDatabase
@EnableJpaAuditing
public abstract class SpringIntegration {
    protected static final String DEFAULT_URL = "http://localhost:7094/balance/";

    protected RestTemplate restTemplate = new RestTemplate();
}
