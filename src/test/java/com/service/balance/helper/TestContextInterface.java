package com.service.balance.helper;

import static com.service.balance.helper.TestContext.CONTEXT;

public interface TestContextInterface {
    default TestContext testContext() {
        return CONTEXT;
    }
}
