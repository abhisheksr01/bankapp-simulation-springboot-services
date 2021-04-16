package com.service.customer.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Customer {
    @Temporal(TemporalType.TIMESTAMP)
    public Date createdAt;
    @Id
    @SequenceGenerator(name = "customer_id_seq", allocationSize = 1, initialValue = 1000)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_id_seq")
    private Integer id;
    private String firstName;
    private String surname;

    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
    }
}
