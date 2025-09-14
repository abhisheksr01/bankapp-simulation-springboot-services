package com.service.balance;

import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.kafka.test.context.EmbeddedKafka;

@SpringBootTest
@AutoConfigureEmbeddedDatabase
@EmbeddedKafka
@EnableJpaAuditing
class BalanceApplicationTest {

    @Test
    void main_contextLoad() {
        BalanceApplication.main(new String[]{});
    }
}
