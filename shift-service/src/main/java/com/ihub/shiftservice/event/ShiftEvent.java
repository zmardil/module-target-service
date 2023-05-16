package com.ihub.shiftservice.event;

import com.ihub.shiftservice.entity.ShiftType;
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
