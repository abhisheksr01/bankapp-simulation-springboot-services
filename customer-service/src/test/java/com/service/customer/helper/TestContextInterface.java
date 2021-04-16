package com.service.customer.helper;

import static com.service.customer.helper.TestContext.CONTEXT;

public interface TestContextInterface {
    default TestContext testContext() {
        return CONTEXT;
    }
}
