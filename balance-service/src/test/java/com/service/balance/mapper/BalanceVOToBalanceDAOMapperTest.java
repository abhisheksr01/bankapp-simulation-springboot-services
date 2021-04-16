package com.service.balance.mapper;

import com.service.balance.helper.TestData;
import com.service.balance.model.BalanceUpdateDAO;
import com.service.balance.model.BalanceUpdateVO;
import org.junit.jupiter.api.Test;

import static com.service.balance.helper.TestData.getBalanceUpdateDAO;
import static com.service.balance.helper.TestData.getBalanceUpdateVO;
import static com.service.balance.mapper.BalanceVOToBalanceDAOMapper.MAPPER;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BalanceVOToBalanceDAOMapperTest {

    @Test
    void map_whenBalanceUpdateVOIsPassed_shouldMapToBalanceUpdateDAO() {
        BalanceUpdateDAO actualBalanceDAO = MAPPER.mapToBalanceUpdateDAO(TestData.getBalanceUpdateVO());

        assertEquals(getBalanceUpdateDAO(), actualBalanceDAO);
    }

    @Test
    void mapToBalanceUpdateVO_whenBalanceUpdateDAOIsPassed_shouldMapToBalanceUpdateVO() {
        BalanceUpdateVO balanceUpdateVO = MAPPER.mapToBalanceUpdateVO(TestData.getBalanceUpdateDAO());

        assertEquals(getBalanceUpdateVO(), balanceUpdateVO);
    }
}