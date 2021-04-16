package com.service.cashier.e2e;

import com.service.cashier.helper.CustomResponse;
import com.service.cashier.helper.SpringIntegration;
import com.service.cashier.helper.TestContextInterface;
import com.service.cashier.model.TransactionVO;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.nl.En;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;

import java.lang.annotation.Annotation;

import static com.service.cashier.helper.TestData.getCreditTransaction;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CashierStepDefinition extends SpringIntegration implements En, TestContextInterface {

    private ResponseEntity responseEntity;

    @Given("Customer provides a valid account number and transaction details")
    public void customer_provides_a_valid_account_number_and_transaction_details() {
        startService();
        stubAccountService();
        testContext().setPayload(getCreditTransaction());
    }

    @When("The cashier makes a call to the service to process the transaction")
    public void the_cashier_makes_a_call_to_the_service_to_process_the_transaction() {
        try {
            responseEntity = restTemplate.postForEntity(DEFAULT_URL, testContext().getPayload(TransactionVO.class),
                    ResponseEntity.class);
        } catch (HttpClientErrorException exception) {
            CustomResponse customResponse = new CustomResponse();
            customResponse.setResponseMessage(exception.getResponseBodyAsString());
            customResponse.setStatusCode(exception.getRawStatusCode());
            testContext().setPayload(customResponse);
        }
    }

    @Then("The API should return status {int}")
    public void the_API_should_return_status(Integer statusCode) {
        assertEquals(HttpStatus.CREATED.value(), responseEntity.getStatusCodeValue());
        stopService();
    }

    @Override
    public String value() {
        return null;
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return null;
    }
}
