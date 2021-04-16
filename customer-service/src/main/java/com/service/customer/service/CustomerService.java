package com.service.customer.service;

import com.service.customer.mapper.CustomerVOToCustomerMapper;
import com.service.customer.model.Customer;
import com.service.customer.model.CustomerVO;
import com.service.customer.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Optional;

@Slf4j
@Service
public class CustomerService {

    private CustomerRepository customerRepository;
    private CustomerVOToCustomerMapper customerVOToCustomerMapper;

    public CustomerService(CustomerVOToCustomerMapper customerVOToCustomerMapper, CustomerRepository customerRepository) {
        this.customerVOToCustomerMapper = customerVOToCustomerMapper;
        this.customerRepository = customerRepository;
    }

    public Customer create(CustomerVO customerVO) {
        log.info("CustomerService : create : Init..");
        log.debug("CustomerService : create : Request customer data : {}", customerVO);
        Customer customer = customerVOToCustomerMapper.map(customerVO);

        log.debug("CustomerService : create : Saving customer data : {}", customerVO);
        Customer savedCustomer = customerRepository.save(customer);

        log.info("CustomerService : create : End..");
        return savedCustomer;
    }

    public Customer getCustomer(int customerId) {
        log.info("CustomerService : getCustomer : Init..");
        log.debug("CustomerService : getCustomer : Finding customer with Id : {}", customerId);
        Optional<Customer> customerOptional = customerRepository.findById(customerId);
        customerOptional.orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "Customer not found"));

        Customer customer = (Customer) customerOptional.get();

        log.debug("CustomerService : getCustomer : Found customer : {}", customer);
        log.info("CustomerService : getCustomer : End..");
        return customer;
    }
}
