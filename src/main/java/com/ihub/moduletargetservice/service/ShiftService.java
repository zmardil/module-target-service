package com.ihub.moduletargetservice.service;

import com.ihub.moduletargetservice.entity.Shift;
import com.ihub.moduletargetservice.enums.ShiftType;
import com.ihub.moduletargetservice.repository.ShiftRepository;
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
