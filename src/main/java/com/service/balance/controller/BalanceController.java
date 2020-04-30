package com.service.balance.controller;

import com.service.balance.model.BalanceUpdateVO;
import com.service.balance.service.BalanceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class BalanceController {
    private BalanceService balanceService;

    public BalanceController(BalanceService balanceService) {
        this.balanceService = balanceService;
    }

    @GetMapping("/getCurrentBalance/{accountNumber}")
    public BalanceUpdateVO getCurrentBalance(@PathVariable int accountNumber) {
        log.info("BalanceController : getCurrentBalance : Init..");
        BalanceUpdateVO currentBalance = this.balanceService.getCurrentBalance(accountNumber);
        log.info("BalanceController : getCurrentBalance : End..");
        return currentBalance;
    }
}
