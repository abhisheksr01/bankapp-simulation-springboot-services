package com.service.transaction.repository;

import com.service.transaction.model.TransactionDAO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<TransactionDAO, Integer> {
    List<TransactionDAO> findTransactionsByAccountNumber(int accountNumber);
}