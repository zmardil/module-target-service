package com.ihub.shifttargetservice.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class ApiError {

    private String path;
    private LocalDateTime timestamp;
    private HttpStatus status;
    private String message;

    public ApiError() {
        this.timestamp = LocalDateTime.now();
    }

    public ApiError(String path, HttpStatus status, String message) {
        this();
        this.path = path;
        this.status = status;
        this.message = message;
    }

}
