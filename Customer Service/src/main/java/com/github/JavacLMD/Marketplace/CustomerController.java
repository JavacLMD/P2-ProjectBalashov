package com.github.JavacLMD.Marketplace;

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
        return customerService.getAllCustomers().log();
    }

    @GetMapping("/{id}")
    public Mono<Customer> findById(@PathVariable int id) {
        return customerService.getCustomerById(id).log();
    }

    @PostMapping("")
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerService.createCustomer(customer);
    }

    @PutMapping("/deposit/{customer}")
    public Mono<Boolean> deposit(@PathVariable int customer, @RequestBody double amt) {
        return customerService.deposit(customer, amt).log();
    }

    @PutMapping("/withdraw/{customer}")
    public Mono<Boolean> withdraw(@PathVariable int customer, @RequestBody double amt) {
        return customerService.withdraw(customer, amt).log();
    }
}