/*
package com.github.ProjectBalashov;
import ch.qos.logback.classic.Logger;
import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.Row;
import com.datastax.oss.driver.api.core.cql.SimpleStatement;
import com.datastax.oss.driver.api.querybuilder.QueryBuilder;
import com.datastax.oss.driver.api.querybuilder.delete.Delete;
import com.datastax.oss.driver.api.querybuilder.insert.Insert;
import com.datastax.oss.driver.api.querybuilder.select.Select;
import com.datastax.oss.driver.api.querybuilder.update.Update;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.Random;

import static com.datastax.oss.driver.api.querybuilder.QueryBuilder.literal;

public class CustomerRepository {
    CqlSession session;
    Logger log;
    public CustomerRepository(CqlSession session, Logger log){
        this.session = session;
        this.log = log;
    }
    public Customer createCustomer(Customer customer){
        log.info("Creating a user: "+customer);
        SimpleStatement simp = SimpleStatement.builder("INSERT INTO Scifi.customer"+
                "(id, balance) " +
                "values (?, ?)")
                .addPositionalValues(customer.getId(), customer.getBalance())
                .build();
        Flux.from(session.executeReactive(simp)).subscribe();
        System.out.println(customer);
        return customer;
    }

    public Mono<Boolean> deposit(int customerId, double amt) {
        boolean flag;
        try {
            //update account value
            getCustomerById(customerId).subscribe(customer -> {

                double newBalance = customer.getBalance() + amt;
                customer.setBalance(newBalance);

                log.info("Attempting to update account balance...");
                Update updateAccount = QueryBuilder.update("scifi", "customer")
                        .setColumn("balance", literal(customer.getBalance()))
                        .whereColumn("account_id").isEqualTo(literal(customerId));
                log.info("Updated account balance to reflect changes.");

                Flux.just(updateAccount.build())
                        .flatMap(session::executeReactive)
                        .subscribe();

            });
            flag = true;
        } catch (RuntimeException e) {
            log.error("Something failed! " + e.getMessage());
            flag = false;
        }
        return Mono.just(flag);
    }

    public Mono<Boolean> withdraw(int customerId, double amt) {
        boolean flag;
        try {
            //update account value
            getCustomerById(customerId).subscribe(customer -> {

                if(customer.getBalance() > amt) {

                    double newBalance = customer.getBalance() - amt;
                    customer.setBalance(newBalance);

                    log.info("Attempting to update account balance...");
                    Update updateAccount = QueryBuilder.update("scifi", "customer")
                            .setColumn("balance", literal(customer.getBalance()))
                            .whereColumn("account_id").isEqualTo(literal(customerId));
                    log.info("Updated account balance to reflect changes.");

                    Flux.just(updateAccount.build())
                            .flatMap(session::executeReactive)
                            .subscribe();
                }
                else{
                    log.info("User did not have enough funds.");
                }

            });
            flag = true;
        } catch (RuntimeException e) {
            log.error("Something failed! " + e.getMessage());
            flag = false;
        }
        return Mono.just(flag);
    }

    public Mono<Customer> getCustomerById(int id) {
        Select select = QueryBuilder.selectFrom("scifi", "customer")
                .columns("id", "balance")
                .whereColumn("id").isEqualTo(literal(id));

        log.info("Getting account by id...");
        return Mono.from(session.executeReactive(select.build()))
                .map(row -> new Customer(row.getInt("id"), row.getDouble("balance")));
    }
    public Flux<Customer> getAllCustomers() {
        Select select = QueryBuilder.selectFrom("scifi", "customer")
                .columns("id", "balance");

        log.info("Getting account by id...");
        return Flux.from(session.executeReactive(select.build()))
                .map(row -> new Customer(row.getInt("id"), row.getDouble("balance")));
    }
    }

 */