package com.service.account.repository;

import com.service.account.model.AccountDAO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<AccountDAO, Integer> {
}
