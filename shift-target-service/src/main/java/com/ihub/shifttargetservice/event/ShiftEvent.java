package com.ihub.shifttargetservice.event;

import com.ihub.shifttargetservice.enums.ShiftType;
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
