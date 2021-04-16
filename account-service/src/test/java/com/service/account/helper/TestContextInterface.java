package com.service.account.helper;

import static com.service.account.helper.TestContext.CONTEXT;

public interface TestContextInterface extends WireMockService {
    default TestContext testContext() {
        return CONTEXT;
    }
}
