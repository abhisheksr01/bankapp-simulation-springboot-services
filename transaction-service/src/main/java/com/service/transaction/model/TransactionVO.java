package com.service.transaction.model;

import lombok.Data;

@Data
public class TransactionVO {
    private int accountNumber;
    private double amount;
    private String transactionType;
}
