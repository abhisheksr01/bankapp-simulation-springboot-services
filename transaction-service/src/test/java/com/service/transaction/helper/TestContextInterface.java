package com.service.transaction.helper;

import static com.service.transaction.helper.TestContext.CONTEXT;

public interface TestContextInterface {
    default TestContext testContext() {
        return CONTEXT;
    }
}
