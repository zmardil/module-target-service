package com.ihub.orderservice.controller;

import com.ihub.orderservice.dto.OrderDTO;
import com.ihub.orderservice.entity.Order;
import com.ihub.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
@CrossOrigin(origins = "${cors.allowed.origins}")
public class OrderController {

    private  final OrderService orderService;

    @PostMapping
    public ResponseEntity<Order> post(@RequestBody OrderDTO orderDTO) {
        return new ResponseEntity<>(orderService.createOrder(orderDTO), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Order>> get() {
        return new ResponseEntity<>(orderService.getAllOrders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> get(@PathVariable String id) {
        return new ResponseEntity<>(orderService.getOrderById(id), HttpStatus.OK);
    }

}

