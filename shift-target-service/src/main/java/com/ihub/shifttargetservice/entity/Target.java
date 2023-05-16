package com.ihub.shifttargetservice.entity;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "targets")
public class Target {

    @Id
    @JsonProperty("shiftId")
    private String id;

    private int quantity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIdentityReference(alwaysAsId = true)
    @JsonProperty("orderId")
    private Order order;

    @OneToOne(fetch = FetchType.EAGER)
    @MapsId
    @JsonIgnore
    private Shift shift;

    // TODO: Add hours column here as per requirement

}
