package com.ihub.shiftservice.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Shift {
    @Id
    @GenericGenerator(
            name = "shift-id-generator",
            strategy = "com.ihub.shiftservice.utils.ShiftIdGenerator"
    )
    @GeneratedValue(
            strategy = GenerationType.IDENTITY,
            generator = "shift-id-generator"
    )
    private String id;
    private String lineId;
    private ShiftType shiftType;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
}
