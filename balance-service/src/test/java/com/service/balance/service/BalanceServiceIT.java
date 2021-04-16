package com.service.balance.service;

import com.service.balance.helper.TestData;
import com.service.balance.model.BalanceUpdateDAO;
import com.service.balance.repository.BalanceRepository;
import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static com.service.balance.helper.TestData.getBalanceUpdateVO;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureEmbeddedDatabase
@ActiveProfiles("test")
public class BalanceServiceIT {

    @Autowired
    private BalanceRepository balanceRepository;
    private BalanceService balanceService;

    @Before
    public void setUp() throws Exception {
        balanceService = new BalanceService(balanceRepository);
    }

    @Test
    public void save_shouldUpdateTheBalance_whenBalanceVOIsProvided() {
        BalanceUpdateDAO balanceUpdateDAO = balanceService.updateBalance(getBalanceUpdateVO());
        assertEquals(getBalanceUpdateVO().getAccountNumber(), balanceUpdateDAO.getAccountNumber());
        assertEquals(getBalanceUpdateVO().getCurrentBalance(), balanceUpdateDAO.getCurrentBalance());
    }
}
