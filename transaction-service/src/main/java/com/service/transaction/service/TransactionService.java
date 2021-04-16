package com.service.transaction.service;

import com.service.transaction.messagingevents.BalanceUpdateEventProducer;
import com.service.transaction.model.BalanceUpdateVO;
import com.service.transaction.model.TransactionDAO;
import com.service.transaction.model.TransactionVO;
import com.service.transaction.repository.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.service.transaction.mapper.TransactionVOToTransactionDAOMapper.MAPPER;

@Slf4j
@Service
public class TransactionService {

    private TransactionRepository transactionRepository;
    private BalanceUpdateEventProducer balanceUpdateEventProducer;

    public TransactionService(TransactionRepository transactionRepository,
                              BalanceUpdateEventProducer balanceUpdateEventProducer) {
        this.transactionRepository = transactionRepository;
        this.balanceUpdateEventProducer = balanceUpdateEventProducer;
    }

    public double processTransaction(TransactionVO transactionVO) {
        log.info("TransactionService : processTransaction : Init..");
        log.debug("TransactionService:processTransaction: TransactionVO : {}", transactionVO);
        double currentBalance = getCurrentBalance(transactionVO.getAccountNumber());
        log.debug("TransactionService:processTransaction: Current balance after calculation : {}", currentBalance);

        if ("credit".equalsIgnoreCase(transactionVO.getTransactionType())) {
            currentBalance += transactionVO.getAmount();
        } else {
            currentBalance -= transactionVO.getAmount();
        }

        log.debug("TransactionService:processTransaction: Publish the updated balance : {}", currentBalance);
        BalanceUpdateVO balanceUpdateVO = new BalanceUpdateVO();
        balanceUpdateVO.setAccountNumber(transactionVO.getAccountNumber());
        balanceUpdateVO.setCurrentBalance(currentBalance);
        this.balanceUpdateEventProducer.publishUpdateBalance(balanceUpdateVO);
        TransactionDAO transactionDAO = MAPPER.map(transactionVO);

        log.debug("TransactionService:processTransaction: TransactionDAO storing the details : {}", transactionDAO);
        this.transactionRepository.save(transactionDAO);
        log.info("TransactionService : processTransaction : End..");

        return currentBalance;
    }

    private double getCurrentBalance(int accountNumber) {
        log.info("TransactionService : getCurrentBalance : Init..");
        List<TransactionDAO> transactionsByAccountNumber = this.transactionRepository
                .findTransactionsByAccountNumber(accountNumber);
        log.debug("TransactionService : getCurrentBalance : For accountNumber : {} the transactions : {}",
                accountNumber, transactionsByAccountNumber);

        double currentBalance = 0;
        for (TransactionDAO transaction : transactionsByAccountNumber) {
            if ("credit".equalsIgnoreCase(transaction.getTransactionType())) {
                currentBalance += transaction.getAmount();
            } else {
                currentBalance -= transaction.getAmount();
            }
        }
        log.info("TransactionService : getCurrentBalance : End..");
        return currentBalance;
    }
}
