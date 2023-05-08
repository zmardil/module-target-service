package com.ihub.moduletargetservice.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "orders")
public class Order {
    @Id
    private String id;
    private int quantity;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<ShiftTarget> shiftTarget;
}
