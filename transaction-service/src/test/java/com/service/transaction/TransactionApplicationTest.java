package com.service.transaction;

import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@AutoConfigureEmbeddedDatabase
@EmbeddedKafka
@EnableJpaAuditing
//@ActiveProfiles("test")
class TransactionApplicationTest {

    @Test
    void main_contextLoad() {
        TransactionApplication.main(new String[]{});
    }
}
