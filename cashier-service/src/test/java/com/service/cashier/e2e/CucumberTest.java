package com.service.cashier.e2e;

import com.service.cashier.CashierApplication;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.ActiveProfiles;

@RunWith(Cucumber.class)
@CucumberContextConfiguration
@SpringBootTest(
        classes = CashierApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
@CucumberOptions(features = "src/test/resources", plugin = {"pretty",
        "html:build/reports/cucumber/cucumber-report.html"})
@EmbeddedKafka
public class CucumberTest {
}
