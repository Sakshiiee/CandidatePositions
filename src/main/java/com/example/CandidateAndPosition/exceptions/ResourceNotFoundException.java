package com.example.CandidateAndPosition.exceptions;

public class ResourceNotFoundException extends RuntimeException {

//    public ResourceNotFoundException() {
//        super("Resource Not Found");
//    }

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValue));
    }

}
