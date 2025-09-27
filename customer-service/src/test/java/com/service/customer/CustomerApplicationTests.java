package com.service.customer;

import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@AutoConfigureEmbeddedDatabase
//@ActiveProfiles("test")
class CustomerApplicationTests {

    @Test
    void main_contextLoad() {
        CustomerApplication.main(new String[]{});
    }
}
