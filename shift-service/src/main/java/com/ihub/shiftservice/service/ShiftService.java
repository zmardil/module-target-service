package com.ihub.shiftservice.service;

import com.ihub.shiftservice.dto.ShiftDTO;
import com.ihub.shiftservice.entity.Shift;
import com.ihub.shiftservice.exception.ResourceNotFoundException;
import com.ihub.shiftservice.repository.ShiftRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShiftService {

    private final ShiftRepository shiftRepository;

    public Shift createShift(ShiftDTO shiftDTO) {
        Shift shift = Shift.builder()
                .lineId(shiftDTO.getLineId())
                .shiftType(shiftDTO.getShiftType())
                .date(shiftDTO.getDate())
                .startTime(shiftDTO.getStartTime())
                .endTime(shiftDTO.getEndTime())
                .build();
        return shiftRepository.save(shift);
    }

    public List<Shift> getAllShifts() {
        return shiftRepository.findAll();
    }

    public Shift getShiftById(String id) {
        return shiftRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Shift of Id " + id + " not found"));
    }
}
