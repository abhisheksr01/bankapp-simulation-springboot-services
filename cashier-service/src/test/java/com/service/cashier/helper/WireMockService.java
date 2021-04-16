package com.service.cashier.helper;

import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
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

    default void stubAccountService() {
        configureFor("localhost", 8005);
        givenThat(get(urlPathMatching("/account/search/1111"))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withBodyFile("response/account-service-response.json")
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)));

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity response = restTemplate.getForEntity
                ("http://localhost:8005/account/search/{customerId}", Object.class, 1111);
        assertEquals(200, response.getStatusCodeValue());
    }

    default void stubAccountNotFoundService() {
        configureFor("localhost", 8005);
        givenThat(get(urlPathMatching("/account/search/2000"))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.NOT_FOUND.value())
                        .withBody("Account Number not found")
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)));
    }

    default void startService() {
        WIRE_MOCK_RULE.start();
    }

    default void stopService() {
        WIRE_MOCK_RULE.stop();
    }
}
