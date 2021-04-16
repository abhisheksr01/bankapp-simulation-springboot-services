package com.service.transaction.e2e;

import com.service.transaction.helper.SpringIntegration;
import com.service.transaction.helper.TestContextInterface;
import com.service.transaction.helper.TestData;
import com.service.transaction.model.TransactionDAO;
import com.service.transaction.model.TransactionVO;
import com.service.transaction.repository.TransactionRepository;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.nl.En;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

import java.lang.annotation.Annotation;
import java.util.List;

import static com.service.transaction.helper.TestData.getCreditTransaction;

public class TransactionStepDefinition extends SpringIntegration implements En, TestContextInterface {

    @Autowired
    KafkaTemplate<String, TransactionVO> testKafkaTemplate;

    @Autowired
    private TransactionRepository transactionRepository;

    @Given("Cashier produces an Credit Transaction event")
    public void cashier_produces_an_Credit_Transaction_event() {
        TransactionVO creditTransaction = getCreditTransaction();
        transactionRepository.save(TestData.getCreditTransactionDAO());
        List<TransactionDAO> all = transactionRepository.findAll();
        testContext().setPayload(getCreditTransaction());
    }

    @When("Transaction service consumes the Credit Transaction event")
    public void transaction_service_consumes_the_Credit_Transaction_event() throws InterruptedException {
        testKafkaTemplate.send("transaction", testContext().getPayload(TransactionVO.class));
        Thread.sleep(1000);
    }

    @Then("The API should return status {int}")
    public void the_API_should_return_status(Integer statusCode) {
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
