package com.service.account.connector;

import com.service.account.model.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class CustomerConnector {

    private String getCustomerEndpoint;
    private RestTemplate restTemplate;

    public CustomerConnector(@Value("${customer.getEndpoint}") String getCustomerEndpoint, RestTemplate restTemplate) {
        this.getCustomerEndpoint = getCustomerEndpoint;
        this.restTemplate = restTemplate;
    }

    public Customer getCustomer(int customerId) {
        log.info("CustomerConnector : getCustomer : Init..");

        ResponseEntity<Customer> responseEntity;
        log.debug("CustomerConnector : getCustomer : Invoking GET Endpoint : {} for customerId",
                getCustomerEndpoint, customerId);
        try {
            responseEntity = this.restTemplate.getForEntity(getCustomerEndpoint, Customer.class, customerId);
        } catch (HttpClientErrorException exception) {
            throw exception.getRawStatusCode() == 404 ?
                    new HttpClientErrorException(HttpStatus.NOT_FOUND, "Customer not found") :
                    new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error");
        }
        log.debug("CustomerConnector: getCustomer : Response is : {}", responseEntity);

        Customer customer = responseEntity.getBody();
        log.info("CustomerConnector : getCustomer : End..");
        return customer;
    }
}
