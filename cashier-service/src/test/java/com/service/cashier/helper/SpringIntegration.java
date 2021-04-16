package com.service.cashier.helper;

import com.service.cashier.CashierApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;

@SpringBootTest(
        classes = CashierApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
@EmbeddedKafka
public abstract class SpringIntegration {
    protected static final String DEFAULT_URL = "http://localhost:7093/cashier/create";

    protected RestTemplate restTemplate = new RestTemplate();
}
