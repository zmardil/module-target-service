package com.ihub.shiftservice.exception;

import com.ihub.shiftservice.exception.ApiError;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.List;

@Data
public class FieldValidationError extends ApiError {

    private List<HashMap<String, String>> errors;

    public FieldValidationError(String path, HttpStatus status, String message, List<HashMap<String, String>> errors) {
        super(path, status, message);
        this.errors = errors;
    }
}