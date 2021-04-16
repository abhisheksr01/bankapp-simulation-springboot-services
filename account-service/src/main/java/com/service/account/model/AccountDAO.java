package com.service.account.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "Account")
public class AccountDAO {
    @Id
    @SequenceGenerator(name = "account_accountNumber_seq", allocationSize = 1, initialValue = 1111)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_accountNumber_seq")
    private Integer accountNumber;
    private Integer customerId;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
    }
}
