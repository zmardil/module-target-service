package com.ihub.shifttargetservice.service;

import com.ihub.shifttargetservice.entity.Shift;
import com.ihub.shifttargetservice.enums.ShiftType;
import com.ihub.shifttargetservice.repository.ShiftRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ShiftService {
    private final ShiftRepository shiftRepository;

    public List<Shift> getAllShifts() {
        return shiftRepository.findAll();
    }

    public List<Shift> getAllShiftsByShiftType(ShiftType shiftType) {
        return shiftRepository.findAllByShiftType(shiftType);
    }
}
