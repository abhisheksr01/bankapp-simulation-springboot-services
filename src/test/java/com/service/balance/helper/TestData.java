package com.service.balance.helper;

import com.service.balance.model.BalanceUpdateDAO;
import com.service.balance.model.BalanceUpdateVO;

import static com.service.balance.helper.TestUtils.convertJsonToObject;

public class TestData {

    public static final int ACCOUNT_NUMBER = 1111;

    public static BalanceUpdateVO getBalanceUpdateVO() {
        return convertJsonToObject("__files/request/balance-update-vo.json", BalanceUpdateVO.class);
    }

    public static BalanceUpdateDAO getBalanceUpdateDAO() {
        return convertJsonToObject("__files/request/balance-update-vo.json", BalanceUpdateDAO.class);
    }
}
