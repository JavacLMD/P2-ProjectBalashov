package com.sakuramatrix.microservicesezin.service;
import com.sakuramatrix.microservicesezin.domain.Orders;
import com.sakuramatrix.microservicesezin.repository.OrdersRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class OrdersService {
    private OrdersRepository ordersRepository;

    public OrdersService(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;}


    public void deleteByOrderId (int orderId) {ordersRepository.deleteByOrderId(orderId).subscribe();}

    public Mono<Orders> saveById (@RequestBody Orders orders) {
        return ordersRepository.save(orders);
    }

    public Flux<Orders> findById (@PathVariable int customer_id) {
        return ordersRepository.findById(customer_id);
    }
    public Flux<Orders> updateById(int customer_id, Orders orders) {
        return findById(customer_id).flatMap(foundOrder ->{
            int item_id=orders.getItem_id();
            double price=orders.getPrice();

            if(item_id > 0 ){
            foundOrder.setItem_id(item_id);}

            if(price > 0 ){
            foundOrder.setPrice(price);}

            return saveById(foundOrder);
                });

    }
   }
