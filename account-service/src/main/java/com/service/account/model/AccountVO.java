package com.service.account.model;

import lombok.Data;

@Data
public class AccountVO {
    private Integer accountNumber;
    private Integer customerId;
    private String firstName;
    private String surname;
}
