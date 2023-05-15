package com.ihub.shiftservice.shift;

import com.ihub.shiftservice.entity.ShiftType;
import com.ihub.shiftservice.validations.EnumConstraint;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class ShiftEvent {
    private String id;
    private String lineId;
    private ShiftType shiftType;
    private LocalDate date;
}
