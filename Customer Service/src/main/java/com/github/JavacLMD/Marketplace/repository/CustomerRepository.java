package com.github.JavacLMD.Marketplace.repository;

import com.github.JavacLMD.Marketplace.domain.Customer;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends ReactiveCassandraRepository<Customer, Integer> {

}
