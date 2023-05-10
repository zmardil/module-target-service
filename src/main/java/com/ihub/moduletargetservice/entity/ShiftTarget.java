package com.ihub.moduletargetservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShiftTarget {

    @Id
    private long id;

    private int target;

    @ManyToOne
    private Order order;

    @OneToOne
    @MapsId
    @JoinColumn
    private Shift shift;

    // TODO: Add hours column here as per requirement

}
