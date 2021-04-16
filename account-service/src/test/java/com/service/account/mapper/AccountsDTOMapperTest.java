package com.service.account.mapper;

import com.service.account.helper.TestData;
import com.service.account.model.AccountCustomerVO;
import com.service.account.model.AccountDAO;
import com.service.account.model.AccountVO;
import com.service.account.model.Customer;
import org.junit.jupiter.api.Test;

import static com.service.account.helper.TestData.getAccountCustomerVO;
import static com.service.account.helper.TestData.getStoredAccountDAO;
import static com.service.account.mapper.AccountsDTOMapper.MAPPER;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AccountsDTOMapperTest {

    @Test
    void mapToAccountCustomerVO_whenAccountsDAOIsPassed_shouldReturnAccountCustomerVo() {
        AccountCustomerVO actualAccountCustomerVO = MAPPER.mapToAccountCustomerVO(getStoredAccountDAO());

        assertEquals(getAccountCustomerVO(), actualAccountCustomerVO);
    }

    @Test
    void mapToAccountVO_AccountsDTOToAccountsVOMapper() {
        AccountDAO storedAccountDAO = getStoredAccountDAO();
        Customer customerData = TestData.getCustomerData();

        AccountVO accountVO = MAPPER.mapToAccountVO(storedAccountDAO, customerData);

        assertEquals(storedAccountDAO.getAccountNumber(), accountVO.getAccountNumber());
        assertEquals(customerData.getId(), accountVO.getCustomerId());
        assertEquals(customerData.getFirstName(), accountVO.getFirstName());
        assertEquals(customerData.getSurname(), accountVO.getSurname());
    }

    @Test
    void mapToAccountCustomerVO_whenAccountDAOIsNull_shouldReturnNull() {
        AccountCustomerVO actualAccountCustomerVO = MAPPER.mapToAccountCustomerVO(null);

        assertEquals(null, actualAccountCustomerVO);

    }
}