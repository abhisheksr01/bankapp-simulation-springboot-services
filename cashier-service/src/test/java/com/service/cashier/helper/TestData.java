package com.service.cashier.helper;

import com.service.cashier.model.TransactionVO;

import static com.service.cashier.helper.TestUtils.convertJsonToObject;

public class TestData {

    public static final int ACCOUNT_NUMBER = 1111;

    public static TransactionVO getCreditTransaction() {
        return convertJsonToObject("__files/request/transaction-credit.json", TransactionVO.class);
    }

    public static TransactionVO geTransactionNonExistingCustomer() {
        return convertJsonToObject("__files/request/transaction-credit-nonexisting-account.json", TransactionVO.class);
    }
}
