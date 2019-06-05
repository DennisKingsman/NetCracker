package com.example.auth.exception;

public class ResourceNotFoundException extends Exception {

    public ResourceNotFoundException(Object resourId) {
        super(resourId != null ? resourId.toString() : null);
    }
}
