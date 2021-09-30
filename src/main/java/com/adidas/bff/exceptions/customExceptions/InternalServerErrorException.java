package com.adidas.bff.exceptions.customExceptions;


public class InternalServerErrorException extends RuntimeException {
    public InternalServerErrorException(String errorMessage){
        super(errorMessage);
    }
}
