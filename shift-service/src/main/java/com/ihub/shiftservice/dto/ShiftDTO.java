package com.ihub.shiftservice.dto;

import com.ihub.shiftservice.entity.ShiftType;
import com.ihub.shiftservice.validations.EnumConstraint;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
public class ShiftDTO {
    @NotBlank
    @Pattern(regexp = "^LN\\d{4}$")
    private String lineId;
    @NotNull
    @EnumConstraint(
        value = ShiftType.class,
        message = "Invalid shift type."
    )
    private ShiftType shiftType;
    @NotNull
    @FutureOrPresent
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
}
