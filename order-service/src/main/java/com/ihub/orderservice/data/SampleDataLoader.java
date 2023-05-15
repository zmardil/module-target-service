package com.ihub.orderservice.data;

import com.ihub.orderservice.dto.OrderDTO;
import com.ihub.orderservice.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@AllArgsConstructor
public class SampleDataLoader implements CommandLineRunner {

    private final OrderService orderService;

    @Override
    public void run(String... args) throws Exception {

        orderService.createOrder(OrderDTO.builder()
                .design("SL T20 Jersey 1996")
                .quantity(100_000)
                .dueDate(LocalDateTime.now().plusMonths(3))
                .build());

        orderService.createOrder(OrderDTO.builder()
                .design("SL T20 Jersey 2024")
                .quantity(200_000)
                .dueDate(LocalDateTime.now().plusMonths(6))
                .build());

    }

}
