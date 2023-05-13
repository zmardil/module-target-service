package com.ihub.shifttargetservice.service;


import com.ihub.shifttargetservice.entity.Order;
import com.ihub.shifttargetservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public void saveOrder(Order order) {

        orderRepository.save(order);
    }

}