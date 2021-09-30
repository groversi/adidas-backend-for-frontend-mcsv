package com.adidas.bff.exceptions.customExceptions;


import com.adidas.bff.exceptions.dto.GatewayErrorDTO;

public class MethodNotAllowedException extends RuntimeException {
    private GatewayErrorDTO gatewayErrorDTO;

    public MethodNotAllowedException(GatewayErrorDTO gatewayErrorDTO){
        this.gatewayErrorDTO = gatewayErrorDTO;
    }

    public GatewayErrorDTO getGatewayErrorDTO() {
        return gatewayErrorDTO;
    }
}
