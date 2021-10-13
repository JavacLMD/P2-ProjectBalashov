package com.github.JavacLMD.Marketplace.domain;

import lombok.Data;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table
@Data
public class Customer {

    @PrimaryKey
    int customerId;
    @Column
    double balance;

    public Customer(int customerId, double balance){
            this.customerId = customerId;
            this.balance = balance;
        }
    }