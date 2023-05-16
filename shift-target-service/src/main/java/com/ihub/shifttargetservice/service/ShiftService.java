package com.ihub.shifttargetservice.service;

import com.ihub.shifttargetservice.entity.Shift;
import com.ihub.shifttargetservice.enums.ShiftType;
import com.ihub.shifttargetservice.event.ShiftEvent;
import com.ihub.shifttargetservice.repository.ShiftRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ShiftService {
    private final ShiftRepository shiftRepository;

    public Shift createShift(ShiftEvent shiftEvent) {
        Shift shift = Shift.builder()
                .id(shiftEvent.getId())
                .lineId(shiftEvent.getLineId())
                .shiftType(shiftEvent.getShiftType())
                .date(shiftEvent.getDate())
                .build();
        return shiftRepository.save(shift);
    }

    public List<Shift> getAllShifts() {
        return shiftRepository.findAll();
    }

    public List<Shift> getAllShiftsByShiftType(ShiftType shiftType) {
        return shiftRepository.findAllByShiftType(shiftType);
    }

    public void deleteShift(ShiftEvent shiftEvent) {
        shiftRepository.deleteById(shiftEvent.getId());
    }
}
