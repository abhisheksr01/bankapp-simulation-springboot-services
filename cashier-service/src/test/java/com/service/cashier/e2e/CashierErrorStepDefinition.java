package com.service.cashier.e2e;

import com.service.cashier.helper.CustomResponse;
import com.service.cashier.helper.TestContextInterface;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.nl.En;

import java.lang.annotation.Annotation;

import static com.service.cashier.helper.TestData.geTransactionNonExistingCustomer;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CashierErrorStepDefinition implements En, TestContextInterface {

    @Given("Customer provides a non existing account number and transaction details")
    public void customer_provides_a_valid_account_number_and_transaction_details() {
        startService();
        stubAccountNotFoundService();
        testContext().setPayload(geTransactionNonExistingCustomer());
    }

    @Then("The API should return error {string} and status code {int}")
    public void the_API_should_return_error_and_status_code(String expectedErrorMsg, Integer expectedStatusCode) {
        CustomResponse customResponse = testContext().getPayload(CustomResponse.class);
        assertEquals(expectedStatusCode, customResponse.getStatusCode());
        assertEquals(expectedErrorMsg, customResponse.getResponseMessage());
        stopService();
        testContext().reset();
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
