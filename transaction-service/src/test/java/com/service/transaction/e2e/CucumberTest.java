package com.service.transaction.e2e;

import com.service.transaction.TransactionApplication;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.spring.CucumberContextConfiguration;
import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.ActiveProfiles;

@RunWith(Cucumber.class)
@CucumberContextConfiguration
@SpringBootTest(
        classes = TransactionApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
@CucumberOptions(features = "src/test/resources", plugin = {"pretty",
        "html:build/reports/cucumber/cucumber-report.html"})
@EmbeddedKafka
@AutoConfigureEmbeddedDatabase
@EnableJpaAuditing
public class CucumberTest {
}
