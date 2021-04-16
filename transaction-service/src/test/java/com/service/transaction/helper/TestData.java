package com.service.transaction.helper;

import com.service.transaction.model.BalanceUpdateVO;
import com.service.transaction.model.TransactionDAO;
import com.service.transaction.model.TransactionVO;

import static com.service.transaction.helper.TestUtils.convertJsonToObject;

public class TestData {

    public static final int ACCOUNT_NUMBER = 1111;

    public static TransactionVO getCreditTransaction() {
        return convertJsonToObject("__files/request/transaction-credit.json", TransactionVO.class);
    }

    public static TransactionVO getDebitTransaction() {
        return convertJsonToObject("__files/request/transaction-debit.json", TransactionVO.class);
    }

    public static TransactionDAO getCreditTransactionDAO() {
        return convertJsonToObject("__files/request/transaction-dto-credit.json", TransactionDAO.class);
    }

    public static TransactionDAO getDebitTransactionDAO() {
        return convertJsonToObject("__files/request/transaction-dto-debit.json", TransactionDAO.class);
    }

    public static TransactionVO getTransactionNonExistingCustomer() {
        return convertJsonToObject("__files/request/transaction-credit-nonexisting-account.json",
                TransactionVO.class);
    }

    public static BalanceUpdateVO getBalanceUpdateEventMessage() {
        return convertJsonToObject("__files/request/balance-update.json", BalanceUpdateVO.class);
    }
}
