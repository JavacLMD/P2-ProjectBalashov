package com.github.JavacLMD.Marketplace;


import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.SimpleStatement;
import com.datastax.oss.driver.api.querybuilder.QueryBuilder;
import com.datastax.oss.driver.api.querybuilder.select.Select;
import com.datastax.oss.driver.api.querybuilder.update.Update;
//import com.github.ProjectBalashov.CustomerRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.datastax.oss.driver.api.querybuilder.QueryBuilder.literal;

@Service
public class CustomerService{

    CqlSession session;
    //Logger log;
    public CustomerService(CqlSession session){
        this.session = session;
        //this.log = log;
    }

    public Customer createCustomer(Customer customer){
        //log.info("Creating a user: "+customer);
        SimpleStatement simp = SimpleStatement.builder("INSERT INTO Scifi.customers"+
                "(customerId, balance) " +
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

                //log.info("Attempting to update account balance...");
                Update updateAccount = QueryBuilder.update("scifi", "customers")
                        .setColumn("balance", literal(customer.getBalance()))
                        .whereColumn("customerId").isEqualTo(literal(customerId));
                //log.info("Updated account balance to reflect changes.");

                Flux.just(updateAccount.build())
                        .flatMap(session::executeReactive)
                        .subscribe();

            });
            flag = true;
        } catch (RuntimeException e) {
            //log.error("Something failed! " + e.getMessage());
            flag = false;
        }
        return Mono.just(flag);
    }

    public Mono<Boolean> withdraw(int customerId, double amt) {
        boolean flag = true;
        try {
            //update account value
            getCustomerById(customerId).subscribe(customer -> {

                if(customer.getBalance() > amt) {

                    double newBalance = customer.getBalance() - amt;
                    customer.setBalance(newBalance);

                    //log.info("Attempting to update account balance...");
                    Update updateAccount = QueryBuilder.update("scifi", "customers")
                            .setColumn("balance", literal(customer.getBalance()))
                            .whereColumn("customerId").isEqualTo(literal(customerId));
                    //log.info("Updated account balance to reflect changes.");

                    Flux.just(updateAccount.build())
                            .flatMap(session::executeReactive)
                            .subscribe();
                }
                else{

                    //log.info("User did not have enough funds.");
                }

            });

        } catch (RuntimeException e) {
            //log.error("Something failed! " + e.getMessage());
            flag = false;
        }
        return Mono.just(flag);
    }

    public Mono<Customer> getCustomerById(int id) {
        Select select = QueryBuilder.selectFrom("scifi", "customers")
                .columns("customerId", "balance")
                .whereColumn("customerId").isEqualTo(literal(id));

        //log.info("Getting account by id...");
        return Mono.from(session.executeReactive(select.build()))
                .map(row -> new Customer(row.getInt("customerId"), row.getDouble("balance")));
    }
    public Flux<Customer> getAllCustomers() {
        Select select = QueryBuilder.selectFrom("scifi", "customers")
                .columns("customerId", "balance");

        //log.info("Getting account by id...");
        return Flux.from(session.executeReactive(select.build()))
                .map(row -> new Customer(row.getInt("customerId"), row.getDouble("balance")));
    }
}