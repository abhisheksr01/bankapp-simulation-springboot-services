package com.service.account;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AccountApplicationTests {

    @Test
    void main_contextLoad() {
        System.setProperty("server.port", "8082");
        AccountApplication.main(new String[]{});
    }
}