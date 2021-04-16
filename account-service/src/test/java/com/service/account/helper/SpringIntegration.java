package com.service.account.helper;

import com.service.account.AccountApplication;
import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;

@SpringBootTest(
        classes = AccountApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
@AutoConfigureEmbeddedDatabase
public abstract class SpringIntegration {
    protected static final String DEFAULT_URL = "http://localhost:7092/account/";
    protected RestTemplate restTemplate = new RestTemplate();
}
