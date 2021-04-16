package com.service.account.helper;

import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.service.account.model.Customer;
import org.junit.Rule;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public interface WireMockService {

    @Rule
    WireMockRule WIRE_MOCK_RULE = new WireMockRule(WireMockConfiguration.wireMockConfig().port(8005));

    default void stubCustomerService() {
        configureFor("localhost", 8005);
        givenThat(get(urlPathMatching("/customer/search/1000"))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withBodyFile("customer-service-response.json")
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)));

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Customer> response = restTemplate.getForEntity
                ("http://localhost:8005/customer/search/{customerId}", Customer.class, 1000);
        assertEquals(200, response.getStatusCodeValue());
    }

    default void stubCustomerServiceNotFound() {
        configureFor("localhost", 8005);
        givenThat(get(urlPathMatching("/customer/search/2000"))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.NOT_FOUND.value())
                        .withBody("Customer not found")
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)));
    }

    default void startService() {
        WIRE_MOCK_RULE.start();
    }

    default void stopService() {
        WIRE_MOCK_RULE.stop();
    }
}
