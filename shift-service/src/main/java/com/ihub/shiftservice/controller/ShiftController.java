package com.ihub.shiftservice.controller;


import com.ihub.shiftservice.dto.ShiftDTO;
import com.ihub.shiftservice.service.ShiftService;
import com.ihub.shiftservice.entity.Shift;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shifts")
@RequiredArgsConstructor
public class ShiftController {

    private final ShiftService shiftService;

    @PostMapping
    public ResponseEntity<Shift> post(@RequestBody @Valid ShiftDTO shiftDTO) {
        return new ResponseEntity<>(shiftService.createShift(shiftDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Shift>> get() {
        return new ResponseEntity<>(shiftService.getAllShifts(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Shift> get(@PathVariable String id) {
        return new ResponseEntity<>(shiftService.getShiftById(id), HttpStatus.OK);
    }
}
