package com.service.customer;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CustomerApplicationTests {

    @Test
    void main_contextLoad() {
        CustomerApplication.main(new String[]{});
    }
}
