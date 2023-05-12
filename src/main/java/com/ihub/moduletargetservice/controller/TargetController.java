package com.ihub.moduletargetservice.controller;

import com.ihub.moduletargetservice.dto.TargetDTO;
import com.ihub.moduletargetservice.service.TargetService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/targets")
public class TargetController {

    private final TargetService targetService;

    @PostMapping
    public ResponseEntity post(
            @RequestBody TargetDTO targetDTO
    ) {
        return new ResponseEntity(HttpStatus.OK);
    }
}
