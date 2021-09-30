package com.adidas.bff.exceptions.customExceptions;


public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String errorMessage){
        super(errorMessage);
    }
}
