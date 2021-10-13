package com.github.JavacLMD.Marketplace;

public class Customer {
    int customerId;
    double balance;
    public Customer(int customerId, double balance){
            this.customerId = customerId;
            this.balance = balance;
        }

        public int getId() {
            return customerId;
        }

        public void setId(int id) {
            this.customerId = id;
        }

        public double getBalance() {
            return balance;
        }

        public void setBalance(double balance) {
            this.balance = balance;
        }
    }