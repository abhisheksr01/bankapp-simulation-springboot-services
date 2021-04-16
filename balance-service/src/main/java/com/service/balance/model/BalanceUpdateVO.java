package com.service.balance.model;

import lombok.Data;

@Data
public class BalanceUpdateVO {
    private int accountNumber;
    private double currentBalance;
}
