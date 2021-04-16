package com.service.transaction;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TransactionApplicationTest {

    @Test
    void main_contextLoad() {
        TransactionApplication.main(new String[]{});
    }
}