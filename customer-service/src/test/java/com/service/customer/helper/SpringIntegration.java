package com.service.customer.helper;

import com.service.customer.CustomerApplication;
import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;

@SpringBootTest(
        classes = CustomerApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
@AutoConfigureEmbeddedDatabase
public abstract class SpringIntegration {
    protected static final String DEFAULT_URL = "http://localhost:7091/customer/";
    protected RestTemplate restTemplate = new RestTemplate();
}
