package com.ihub.orderservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class OrderEntity {

    @Id
    private String orderId;

    private int qty;

    private String design;

    private LocalDateTime createdAt;

    private LocalDateTime dueDate;

    private String status;

}