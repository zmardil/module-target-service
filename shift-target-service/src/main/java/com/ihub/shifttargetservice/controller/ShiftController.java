package com.ihub.shifttargetservice.controller;

import com.ihub.shifttargetservice.entity.Shift;
import com.ihub.shifttargetservice.enums.ShiftType;
import com.ihub.shifttargetservice.service.ShiftService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("shifts")
@AllArgsConstructor
@CrossOrigin(origins = "${cors.allowed.origins}")
public class ShiftController {
    private final ShiftService shiftService;

    @GetMapping
    public ResponseEntity<List<Shift>> get(@RequestParam Optional<ShiftType> shiftType) {
        return shiftType.isPresent() ? new ResponseEntity<>(shiftService.getAllShiftsByShiftType(shiftType.get()), HttpStatus.OK)
                : new ResponseEntity<>(shiftService.getAllShifts(), HttpStatus.OK);
    }
}
