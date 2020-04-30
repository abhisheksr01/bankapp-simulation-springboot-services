package com.service.balance.repository;

import com.service.balance.model.BalanceUpdateDAO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BalanceRepository extends JpaRepository<BalanceUpdateDAO, Integer> {
}