package com.ihub.moduletargetservice.entity;

import com.ihub.moduletargetservice.enums.ShiftType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "shifts")
@Table(uniqueConstraints = {@UniqueConstraint(name = "LineIdAndShiftTypeAndDate", columnNames = {"lineId", "ShiftType", "date"})})
public class Shift {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String lineId;
    @Enumerated(EnumType.STRING)
    private ShiftType shiftType;
    private LocalDate date;
    @OneToOne(mappedBy = "shift", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private ShiftTarget target;
}
