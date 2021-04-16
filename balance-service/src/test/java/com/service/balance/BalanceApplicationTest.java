package com.service.balance;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BalanceApplicationTest {

    @Test
    void main_contextLoad() {
        BalanceApplication.main(new String[]{});
    }
}