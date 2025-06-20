package com.example.CandidateAndPosition.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    // Handle Resource Not Found
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleResourceNotFound(ResourceNotFoundException ex) {
        logger.info("ResourceNotFoundException handled");

        Map<String, Object> response = new HashMap<>();
        response.put("message", ex.getMessage());
        response.put("success", false);
        response.put("status", HttpStatus.NOT_FOUND.value());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    // Handle Bad API Request
    @ExceptionHandler(BadApiRequestException.class)
    public ResponseEntity<Map<String, Object>> handleBadApiRequest(BadApiRequestException ex) {
        logger.info("BadApiRequestException handled");

        Map<String, Object> response = new HashMap<>();
        response.put("message", ex.getMessage());
        response.put("success", false);
        response.put("status", HttpStatus.BAD_REQUEST.value());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }




    // Handle Method Argument Validation Errors
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<Map<String, Object>> handleValidationErrors(MethodArgumentNotValidException ex) {
//        Map<String, Object> errors = new HashMap<>();
//        List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();
//
//        for (ObjectError objectError : allErrors) {
//            String field = ((FieldError) objectError).getField();
//            String message = objectError.getDefaultMessage();
//            errors.put(field, message);
//        }
//
//        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
//    }

}
