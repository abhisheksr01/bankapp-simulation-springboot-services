package com.service.transaction.repository;

import com.service.transaction.helper.TestData;
import com.service.transaction.model.TransactionDAO;
import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static com.service.transaction.helper.TestData.getCreditTransactionDAO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureEmbeddedDatabase
@ActiveProfiles("test")
public class TransactionRepositoryTest {

    @Autowired
    private TransactionRepository transactionRepository;

    @Test
    public void save_whenAccountDAOIsPassed_shouldCreateAccountAndStoreData() {
        TransactionDAO transactionDAO = transactionRepository.save(getCreditTransactionDAO());

        assertEquals(1111, transactionDAO.getAccountNumber());
        Assertions.assertEquals(50.0, transactionDAO.getAmount());
        Assertions.assertEquals("credit", transactionDAO.getTransactionType());
        assertNotNull(transactionDAO.getCreatedAt());
    }

    @Test
    public void save_whenAccountNumberIsPassed_shouldReturnListOfTransactionDetailsAssociated() {
        transactionRepository.save(getCreditTransactionDAO());
        transactionRepository.save(getCreditTransactionDAO());
        transactionRepository.save(getCreditTransactionDAO());
        TransactionDAO creditTransactionDAO = getCreditTransactionDAO();
        creditTransactionDAO.setAccountNumber(1234);
        transactionRepository.save(creditTransactionDAO);
        transactionRepository.save(TestData.getDebitTransactionDAO());

        List<TransactionDAO> transactionDAOList = transactionRepository.findAll();
        List<TransactionDAO> transactionsByAccountNumber = transactionRepository.findTransactionsByAccountNumber(getCreditTransactionDAO().getAccountNumber());

        Assert.assertEquals(4, transactionsByAccountNumber.size());
        Assert.assertEquals(5, transactionDAOList.size());
        Assert.assertEquals(TestData.getCreditTransaction().getAccountNumber(), transactionsByAccountNumber.get(0).getAccountNumber());
    }
}
