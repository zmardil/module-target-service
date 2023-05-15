package com.ihub.orderservice.controller;

import com.ihub.orderservice.event.Order;
import com.ihub.orderservice.entity.OrderEntity;
import com.ihub.orderservice.kafka.OrderProducer;
import com.ihub.orderservice.repository.OrderRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1")
public class OrderController {


    private OrderProducer orderProducer;
    private OrderRepository orderRepository;

    public OrderController(OrderProducer orderProducer, OrderRepository orderRepository) {
        this.orderProducer = orderProducer;
        this.orderRepository = orderRepository;
    }


    @PostMapping("/orders")
    public String placeOrder(@RequestBody OrderEntity orderEntity){

        orderEntity.setCreatedAt(LocalDateTime.now());
        orderRepository.save(orderEntity);

        Order orderEvent = new Order();
        orderEvent.setOrderId(orderEntity.getOrderId());
        orderEvent.setQty(orderEntity.getQty());

        orderProducer.sendMessage(orderEvent,"create");

        return "Order placed sucessfully";
    }
}

