package com.adidas.bff.exceptions.customExceptions;


import com.adidas.bff.exceptions.dto.GatewayErrorDTO;

public class MethodForbidenException extends RuntimeException {
    private GatewayErrorDTO gatewayErrorDTO;

    public MethodForbidenException(GatewayErrorDTO gatewayErrorDTO){
        this.gatewayErrorDTO = gatewayErrorDTO;
    }

    public GatewayErrorDTO getGatewayErrorDTO() {
        return gatewayErrorDTO;
    }
}
