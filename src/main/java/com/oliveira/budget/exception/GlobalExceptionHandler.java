package com.oliveira.budget.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleNotFound(ResourceNotFoundException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("error", ex.getMessage());
        response.put("status", HttpStatus.NOT_FOUND.toString());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(InvalidLengthException.class)
    public ResponseEntity<Map<String, String>> handleInvalidLength(ResourceNotFoundException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("error", ex.getMessage());
        response.put("status", HttpStatus.BAD_REQUEST.toString());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(InvalidInputException.class)
    public ResponseEntity<Map<String, String>> invalidInputException(ResourceNotFoundException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("error", ex.getMessage());
        response.put("status", HttpStatus.BAD_REQUEST.toString());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(RepeatedException.class)
    public ResponseEntity<Map<String, String>> repeatedException(ResourceNotFoundException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("error", ex.getMessage());
        response.put("status", HttpStatus.BAD_REQUEST.toString());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(WrongPasswordException.class)
    public ResponseEntity<Map<String, String>> wrongPasswordException(ResourceNotFoundException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("error", ex.getMessage());
        response.put("status", HttpStatus.UNAUTHORIZED.toString());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }
}
