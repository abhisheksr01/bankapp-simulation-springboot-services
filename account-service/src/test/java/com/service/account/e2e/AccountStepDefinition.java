package com.service.account.e2e;

import com.service.account.helper.CustomResponse;
import com.service.account.helper.SpringIntegration;
import com.service.account.helper.TestContextInterface;
import com.service.account.model.AccountCustomerVO;
import com.service.account.model.AccountVO;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.nl.En;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;

import java.lang.annotation.Annotation;

import static com.service.account.helper.TestData.getAccountCustomerVO;
import static java.lang.Integer.valueOf;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AccountStepDefinition extends SpringIntegration implements En, TestContextInterface {

    private AccountVO customer = new AccountVO();
    private ResponseEntity accountResponseEntity;
    private CustomResponse customResponse = new CustomResponse();

    @Given("Customer provides a valid customer id {string}")
    public void customer_provides_a_valid_customer_id(String customerId) {
        startService();
        stubCustomerService();
        testContext().set("customerId", customerId);
    }

    @When("The customer makes a call to create an account")
    public void the_customer_makes_a_call_to_create_an_account() {
        try {
            accountResponseEntity = restTemplate.postForEntity(DEFAULT_URL + "/create/" + testContext().get("customerId"), null, AccountVO.class);
        } catch (HttpClientErrorException exception) {
            customResponse.setStatusCode(exception.getRawStatusCode());
            customResponse.setResponseMessage(exception.getResponseBodyAsString());
            testContext().setPayload(customResponse);
        }
    }

    @Then("The API should return the account and customer details and status {int}")
    public void the_API_should_return_the_account_and_customer_details(int statusCode) {
        assertEquals(statusCode, accountResponseEntity.getStatusCodeValue());
        AccountVO account = (AccountVO) accountResponseEntity.getBody();
        assertEquals("Robo", account.getFirstName());
        assertEquals("Cop", account.getSurname());
        assertEquals(valueOf(testContext().get("customerId")), account.getCustomerId());
        assertNotNull(account.getAccountNumber());
        stopService();
        testContext().reset();
    }

    @Given("Customer provides a account number {string}")
    public void customer_provides_a_account_number(String accountNumber) {
        testContext().set("accountNumber", accountNumber);
    }

    @When("The customer makes a call to get account details")
    public void the_customer_makes_a_call_to_get_account_details() {
        try {
            accountResponseEntity = restTemplate.getForEntity(DEFAULT_URL + "/search/"
                    + testContext().get("accountNumber"), AccountCustomerVO.class);
            testContext().setPayload(accountResponseEntity);
        } catch (HttpClientErrorException exception) {
            customResponse.setStatusCode(exception.getRawStatusCode());
            customResponse.setResponseMessage(exception.getResponseBodyAsString());
            testContext().setPayload(customResponse);
        }
    }

    @Then("The API should return the account details and status {int}")
    public void the_API_should_return_the_account_details_and_status(int statusCode) {
        assertEquals(getAccountCustomerVO(), accountResponseEntity.getBody());
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
