package com.github.JavacLMD.Marketplace.controller;

import com.github.JavacLMD.Marketplace.service.CustomerService;
import com.github.JavacLMD.Marketplace.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("")
    public Flux<Customer> findAll() {
        return customerService.findAllCustomers().log();
    }

    @PostMapping("")
    public Mono<Customer> createCustomer(@RequestBody Customer customer) {
        return customerService.createCustomer(customer);
    }

    @GetMapping("/{id}")
    public Mono<Customer> findById(@PathVariable int id) {
        return customerService.findCustomerById(id).log();
    }

    @PutMapping("/deposit/{customer}")
    public Mono<Customer> deposit(@PathVariable int id, @RequestBody double amt) {
        return customerService.deposit(id, amt).log();
    }

    @PutMapping("/withdraw/{customer}")
    public Mono<Customer> withdraw(@PathVariable int id, @RequestBody double amt) {
        return customerService.withdraw(id, amt).log();
    }
}