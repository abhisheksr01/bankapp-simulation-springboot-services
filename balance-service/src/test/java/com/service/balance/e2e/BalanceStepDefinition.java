package com.service.balance.e2e;

import com.service.balance.helper.SpringIntegration;
import com.service.balance.helper.TestContextInterface;
import com.service.balance.helper.TestData;
import com.service.balance.model.BalanceUpdateDAO;
import com.service.balance.model.BalanceUpdateVO;
import com.service.balance.repository.BalanceRepository;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.nl.En;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.client.HttpClientErrorException;

import java.lang.annotation.Annotation;
import java.util.Optional;

import static com.service.balance.helper.TestData.ACCOUNT_NUMBER;
import static com.service.balance.helper.TestData.getBalanceUpdateVO;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BalanceStepDefinition extends SpringIntegration implements En, TestContextInterface {

    @Autowired
    KafkaTemplate<String, BalanceUpdateVO> testKafkaTemplate;

    @Autowired
    private BalanceRepository balanceRepository;

    private int AccountNumber;
    private Integer accountNumber;
    private ResponseEntity<BalanceUpdateVO> balanceResponseEntity;

    @Given("Transaction service has calculated the balance")
    public void transaction_service_has_calculated_the_balance() {
        testContext().setPayload(getBalanceUpdateVO());
    }

    @When("Balance service consumes the Balance Update event")
    public void balance_service_consumes_the_Balance_Update_event() throws InterruptedException {
        testKafkaTemplate.send("balance", testContext().getPayload(BalanceUpdateVO.class));
        Thread.sleep(1000);
    }

    @Then("The current balance should get updated")
    public void the_current_balance_should_get_updated() {
        Optional<BalanceUpdateDAO> optionalBalanceUpdateDAO = balanceRepository.findById(ACCOUNT_NUMBER);
        BalanceUpdateDAO balanceUpdateDAO = optionalBalanceUpdateDAO.get();
        assertEquals(80.0, balanceUpdateDAO.getCurrentBalance());
        assertEquals(ACCOUNT_NUMBER, balanceUpdateDAO.getAccountNumber());
    }

    @Given("The customer has provided the account number {int}")
    public void the_customer_has_provided_the_account_number(Integer accountNumber) {
        BalanceUpdateDAO balanceUpdateDAO = TestData.getBalanceUpdateDAO();
        balanceUpdateDAO.setAccountNumber(accountNumber);
        balanceRepository.save(balanceUpdateDAO);
        this.accountNumber = accountNumber;
    }

    @When("Customer makes a call to get the current balance")
    public void customer_makes_a_call_to_get_the_current_balance() {
        try {
            balanceResponseEntity = restTemplate.getForEntity(DEFAULT_URL + "getCurrentBalance/" + this.accountNumber,
                    BalanceUpdateVO.class);
            testContext().setPayload(balanceResponseEntity);
        } catch (HttpClientErrorException exception) {
//            customResponse.setStatusCode(exception.getRawStatusCode());
//            customResponse.setResponseMessage(exception.getResponseBodyAsString());
//            testContext().setPayload(customResponse);
        }
    }

    @Then("Customer should see the current account balance")
    public void customer_should_see_the_current_account_balance() {
        BalanceUpdateVO balance = balanceResponseEntity.getBody();
        assertEquals(80.0, balance.getCurrentBalance());
        assertEquals(this.accountNumber, balance.getAccountNumber());
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
