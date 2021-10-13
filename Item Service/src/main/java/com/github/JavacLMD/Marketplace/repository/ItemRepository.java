package com.github.JavacLMD.Marketplace.repository;

import com.github.JavacLMD.Marketplace.domain.Item;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;

import reactor.core.publisher.Flux;

public interface ItemRepository extends ReactiveCassandraRepository<Item, Integer>{

    @Query(value = "SELECT * FROM scifi.items WHERE category CONTAINS ?0 ALLOW FILTERING", allowFiltering = true)
    Flux<Item> findByCategory(String category);

    @Query(value = "SELECT * FROM scifi.items WHERE name = ?0 ALLOW FILTERING", allowFiltering = true)
    Flux<Item> findByName(String name);

}