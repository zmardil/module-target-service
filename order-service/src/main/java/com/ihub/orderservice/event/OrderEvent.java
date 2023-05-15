package com.ihub.orderservice.event;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderEvent {
    private String id;
    private int quantity;
}
