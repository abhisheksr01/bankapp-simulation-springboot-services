package com.service.cashier.connector;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class AccountConnector {

    private RestTemplate restTemplate;
    private String getAccountEndpoint;

    public AccountConnector(@Value("${account.getEndpoint}") String getAccountEndpoint,
                            RestTemplate restTemplate) {
        this.getAccountEndpoint = getAccountEndpoint;
        this.restTemplate = restTemplate;
    }

    public void invokeAccountService(int accountNumber) {
        log.info("AccountConnector : callAccountService : Init..");

        log.debug("AccountConnector : callAccountService : Invoking GET Endpoint : {} for accountNumber : {}",
                this.getAccountEndpoint, accountNumber);
        ResponseEntity responseEntity = this.restTemplate.getForEntity
                (this.getAccountEndpoint, Object.class, accountNumber);
        log.debug("AccountConnector: callAccountService : Response is : {}", responseEntity);
        log.info("AccountConnector : callAccountService : End..");
    }
}
