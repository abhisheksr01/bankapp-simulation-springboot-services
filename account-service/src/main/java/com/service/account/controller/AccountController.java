package com.service.account.controller;

import com.service.account.model.AccountCustomerVO;
import com.service.account.model.AccountVO;
import com.service.account.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class AccountController {

    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create/{customerId}")
    public AccountVO create(@PathVariable int customerId) {
        log.info("AccountController : create : Init..");

        AccountVO accountVO = accountService.create(customerId);

        log.info("AccountController : create : End..");
        return accountVO;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/search/{accountNumber}")
    public AccountCustomerVO getAccountDetails(@PathVariable int accountNumber) {
        log.info("AccountController : getAccountDetails : Init..");

        AccountCustomerVO accountDetails = this.accountService.getAccountDetails(accountNumber);

        log.info("AccountController : getAccountDetails : End..");
        return accountDetails;
    }
}
