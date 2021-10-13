package com.sakuramatrix.microservicesezin.controller;

import com.sakuramatrix.microservicesezin.domain.Orders;
import com.sakuramatrix.microservicesezin.repository.OrdersRepository;
import com.sakuramatrix.microservicesezin.service.OrdersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/orders")
public class OrdersController {

    private static final Logger log = (Logger) LoggerFactory.getLogger(OrdersController.class);
    private OrdersService ordersService;

    public OrdersController(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    @Autowired
    private OrdersRepository ordersRepository;

    @GetMapping("/{customer_id}")
    public Flux<Orders> findById(@PathVariable int customer_id) {
        log.info("Finding order by customer id");
        return ordersRepository.findById(customer_id);
    }

    @GetMapping("")
    public Flux<Orders> findAll() {
        log.info("Finding all orders ");
        return ordersRepository.findAll();
    }

    @PostMapping("/{customer_id}")
    public Mono<Orders> saveById(@RequestBody Orders orders) {
        log.info("Saving an order by customer id ");
        return ordersService.saveById(orders);
    }

    @PutMapping("/update/{customer_id}")
    public Flux<Orders> updateById(@PathVariable int customer_id, @RequestBody Orders orders) {
        log.info("Updating an order by customer id ");
        return ordersService.updateById(customer_id, orders);
    }

    @DeleteMapping("/{orderId}")
    public void deleteByOrderId(@PathVariable int orderId) {
        log.info("Deleting an order by order id ");
        ordersService.deleteByOrderId(orderId);

    }
}

