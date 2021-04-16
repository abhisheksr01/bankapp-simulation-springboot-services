package com.service.customer.controller;

import com.service.customer.model.Customer;
import com.service.customer.model.CustomerVO;
import com.service.customer.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
public class CustomerController {

    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Customer create(@RequestBody @Valid CustomerVO customerVO) {
        log.info("CustomerController : create : Init..");

        Customer customer = customerService.create(customerVO);

        log.info("CustomerController : create : End..");
        return customer;
    }

    @GetMapping("/search/{customerId}")
    @ResponseStatus(HttpStatus.OK)
    public Customer getCustomer(@PathVariable int customerId) {
        log.info("CustomerController : getCustomer : Init..");

        Customer customer = customerService.getCustomer(customerId);

        log.info("CustomerController : getCustomer : End..");
        return customer;
    }
}
