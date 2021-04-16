package com.service.transaction.helper;

import com.service.transaction.TransactionApplication;
import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;

@SpringBootTest(
        classes = TransactionApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
@EmbeddedKafka
@AutoConfigureEmbeddedDatabase
@EnableJpaAuditing
public abstract class SpringIntegration {
    protected static final String DEFAULT_URL = "http://localhost:7093/cashier/create";

    protected RestTemplate restTemplate = new RestTemplate();
}
