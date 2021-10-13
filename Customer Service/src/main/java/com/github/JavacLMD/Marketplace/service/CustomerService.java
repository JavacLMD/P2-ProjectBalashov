package com.github.JavacLMD.Marketplace.service;

import com.github.JavacLMD.Marketplace.domain.Customer;
import com.github.JavacLMD.Marketplace.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerService{

    @Autowired
    private CustomerRepository repository;


    public Mono<Customer> findCustomerById(int id) {
        return repository.findById(id).log();
    }

    public Flux<Customer> findAllCustomers() {
        return repository.findAll().log();
    }

    public Mono<Customer> createCustomer(Customer customer) {
        return repository.save(customer).log();
    }

    public Mono<Customer> deposit(int id, double amount) {
        return repository.findById(id)
                .flatMap(customer -> {

                    double current = customer.getBalance();
                    current += amount;
                    customer.setBalance(current);

                    return Mono.just(customer);
                }).log();
    }

    public Mono<Customer> withdraw(int id, double amount) {
        return repository.findById(id)
                .flatMap(customer -> {
                    double current = customer.getBalance();
                    current -= amount;
                    customer.setBalance(current);
                    return Mono.just(customer);
                }).log();
    }






}