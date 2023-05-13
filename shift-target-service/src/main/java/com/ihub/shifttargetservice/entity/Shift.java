package com.ihub.shifttargetservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.ihub.shifttargetservice.enums.ShiftType;
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
@Table(uniqueConstraints = {@UniqueConstraint(
        name = "LineIdAndShiftTypeAndDate",
        columnNames = {"lineId", "ShiftType", "date"}
)})
public class Shift {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    private String lineId;

    @Enumerated(EnumType.STRING)
    private ShiftType shiftType;

    private LocalDate date;

    @OneToOne(mappedBy = "shift", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @PrimaryKeyJoinColumn
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Target target;
}
