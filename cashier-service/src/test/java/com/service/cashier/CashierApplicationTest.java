package com.service.cashier;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CashierApplicationTests {

    @Test
    void main_contextLoad() {
        CashierApplication.main(new String[]{});
    }
}