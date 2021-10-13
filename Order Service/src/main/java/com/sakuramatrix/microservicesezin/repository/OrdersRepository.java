package com.sakuramatrix.microservicesezin.repository;

import com.sakuramatrix.microservicesezin.domain.Orders;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface OrdersRepository extends ReactiveCassandraRepository <Orders, Integer> {

    @AllowFiltering
    Flux<Orders> findById (int customer_id);

    @AllowFiltering
    Flux<Orders> deleteByOrderId (int orderId);

}
