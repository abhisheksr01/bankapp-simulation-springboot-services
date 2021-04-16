package com.service.customer.e2e;

import com.service.customer.helper.CustomResponseEntity;
import com.service.customer.helper.TestContextInterface;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.nl.En;

import java.lang.annotation.Annotation;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomerErrorStepDefinition implements En, TestContextInterface {

    @Then("The API should return error {string} and status code {int}")
    public void the_API_should_return_error_and_status_code(String expectedErrorMsg, Integer expectedStatusCode) {
        CustomResponseEntity customResponseEntity = testContext().getPayload(CustomResponseEntity.class);
        assertEquals(expectedStatusCode, customResponseEntity.getStatusCode());
        assertEquals(expectedErrorMsg, customResponseEntity.getResponseMessage());
        testContext().reset();
    }

    @Given("Customer provides a invalid customer id {string}")
    public void customer_provides_a_invalid_customer_id(String customerId) {
        testContext().set("customerId", customerId);
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
