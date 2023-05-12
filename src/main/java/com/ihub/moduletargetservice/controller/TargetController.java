package com.ihub.moduletargetservice.controller;

import com.ihub.moduletargetservice.dto.TargetDTO;
import com.ihub.moduletargetservice.service.TargetService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/targets")
@CrossOrigin(origins = "${cors.allowed.origins}")
public class TargetController {

    private final TargetService targetService;

    @PostMapping
    public ResponseEntity post(
            @RequestBody TargetDTO targetDTO
    ) {
        return new ResponseEntity(HttpStatus.OK);
    }
}
