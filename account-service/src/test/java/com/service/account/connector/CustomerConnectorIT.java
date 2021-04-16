package com.service.account.connector;

import com.service.account.helper.WireMockService;
import com.service.account.model.Customer;
import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest()
@AutoConfigureEmbeddedDatabase
@ActiveProfiles("test")
class CustomerConnectorIT implements WireMockService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${customer.getEndpoint}")
    private String getCustomerEndpoint;

    @BeforeEach
    void setUp() {
        startService();
        stubCustomerService();
    }

    @Test
    void getCustomer_whenAValidCustomerIdIsPassed_shouldReturnTheCustomerData() {
        CustomerConnector customerConnector = new CustomerConnector(getCustomerEndpoint, restTemplate);

        Customer customer = customerConnector.getCustomer(1000);

        assertEquals("Robo", customer.getFirstName());
        assertEquals("Cop", customer.getSurname());
        assertEquals(1000, customer.getId());
        stopService();
    }
}