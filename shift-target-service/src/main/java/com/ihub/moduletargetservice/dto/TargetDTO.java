package com.ihub.moduletargetservice.dto;

import com.ihub.moduletargetservice.enums.ShiftType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class TargetDTO {
    @NotBlank
    @Pattern(regexp = "^LN\\d{4}$")
    private String lineId;
    @NotNull
    private ShiftType shiftType;
    @NotNull
    private LocalDate date;
    @NotBlank
    @Pattern(regexp = "^OD\\d{4}$")
    private String orderId;
    @NotNull
    @Positive
    private int quantity;
}
