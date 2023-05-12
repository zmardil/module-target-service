package com.ihub.moduletargetservice.controller;

import com.ihub.moduletargetservice.entity.Shift;
import com.ihub.moduletargetservice.enums.ShiftType;
import com.ihub.moduletargetservice.service.ShiftService;
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
