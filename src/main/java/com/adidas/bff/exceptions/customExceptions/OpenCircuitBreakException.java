package com.adidas.bff.exceptions.customExceptions;


import com.adidas.bff.exceptions.dto.GatewayErrorDTO;
import com.adidas.bff.exceptions.dto.OpenCircuitBreakDTO;

public class OpenCircuitBreakException extends RuntimeException {
    private OpenCircuitBreakDTO openCircuitBreakDTO;

    public OpenCircuitBreakException(){
        this.openCircuitBreakDTO = new OpenCircuitBreakDTO();
    }

    public OpenCircuitBreakDTO getOpenCircuitBreakException() {
        return openCircuitBreakDTO;
    }
}
