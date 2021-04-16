package com.service.transaction.mapper;

import com.service.transaction.helper.TestData;
import com.service.transaction.model.TransactionDAO;
import org.junit.jupiter.api.Test;

import static com.service.transaction.helper.TestData.getCreditTransactionDAO;
import static com.service.transaction.mapper.TransactionVOToTransactionDAOMapper.MAPPER;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TransactionVOToTransactionDAOMapperTest {

    @Test
    void map_whenTransactionVOIsPassed_shouldMapToTransactionDTO() {
        TransactionDAO actualTransactionDAO = MAPPER.map(TestData.getCreditTransaction());

        assertEquals(getCreditTransactionDAO(), actualTransactionDAO);
    }
}