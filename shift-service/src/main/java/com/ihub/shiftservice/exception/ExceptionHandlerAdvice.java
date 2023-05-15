package com.ihub.shiftservice.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;

@RestControllerAdvice
public class ExceptionHandlerAdvice {
    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public ResponseEntity handleMethodArgumentNotFound(
            MethodArgumentNotValidException ex,
            HttpServletRequest request
    ) {
        HashMap<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(
                err -> errors.put(
                        err.getField(),
                        err.getDefaultMessage()
                )
        );
        FieldValidationError apiError = new FieldValidationError(
                request.getRequestURI(),
                HttpStatus.CONFLICT,
                "Data integrity violation",
                List.of(errors)
        );
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> handleResourceNotFoundException(ResourceNotFoundException ex, HttpServletRequest request) {
        ApiError apiError = new ApiError(
                request.getRequestURI(),
                HttpStatus.NOT_FOUND,
                ex.getMessage()
        );
        return new ResponseEntity(apiError, apiError.getStatus());
    }

    @ExceptionHandler(value = {ResourceAlreadyExistsException.class, IllegalArgumentException.class })
    public ResponseEntity<ApiError> handleBadRequestExceptions(RuntimeException ex, HttpServletRequest request) {
        ApiError apiError = new ApiError(
                request.getRequestURI(),
                HttpStatus.BAD_REQUEST,
                ex.getMessage()
        );
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}
