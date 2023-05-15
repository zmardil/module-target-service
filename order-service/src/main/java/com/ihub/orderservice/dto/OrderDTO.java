package com.ihub.orderservice.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

// TODO: add validation
@Data
@Builder
public class OrderDTO {

    private int quantity;

    private String design;

    private LocalDateTime dueDate;

}
