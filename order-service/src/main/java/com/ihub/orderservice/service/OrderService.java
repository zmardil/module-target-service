package com.ihub.orderservice.service;

import com.ihub.orderservice.dto.OrderDTO;
import com.ihub.orderservice.entity.Order;
import com.ihub.orderservice.event.OrderEvent;
import com.ihub.orderservice.exception.ResourceNotFoundException;
import com.ihub.orderservice.kafka.OrderProducer;
import com.ihub.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    private final OrderProducer orderProducer;

    public Order createOrder(OrderDTO orderDTO) {
        Order order = Order.builder()
                .design(orderDTO.getDesign())
                .quantity(orderDTO.getQuantity())
                .dueDate(orderDTO.getDueDate())
                .build();
        orderRepository.save(order);

        OrderEvent orderEvent = OrderEvent.builder()
                .id(order.getId())
                .quantity(order.getQuantity())
                .build();

        orderProducer.sendMessage(orderEvent, "create");

        return order;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(String id) {
        return orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("order of id " + id + " not found"));
    }
}
