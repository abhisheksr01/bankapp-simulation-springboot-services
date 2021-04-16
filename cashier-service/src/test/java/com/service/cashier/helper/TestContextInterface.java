package com.service.cashier.helper;

import static com.service.cashier.helper.TestContext.CONTEXT;

public interface TestContextInterface extends WireMockService {
    default TestContext testContext() {
        return CONTEXT;
    }
}
