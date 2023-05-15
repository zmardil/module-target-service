package com.ihub.orderservice.entity;

import com.ihub.orderservice.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GenericGenerator(
            name = "order-id-generator",
            strategy = "com.ihub.orderservice.utils.OrderIdGenerator"
    )
    @GeneratedValue(
            strategy = GenerationType.IDENTITY,
            generator = "order-id-generator"
    )
    private String id;

    private int quantity;

    private String design;

    @CreationTimestamp
    private LocalDateTime createdAt;

    private LocalDateTime dueDate;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Status status = Status.PENDING;

}