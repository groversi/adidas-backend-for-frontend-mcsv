package com.adidas.bff.exceptions.customExceptions;


import com.adidas.bff.exceptions.dto.GatewayErrorDTO;

public class BadRequestException extends RuntimeException {
    private GatewayErrorDTO gatewayErrorDTO;

    public BadRequestException(GatewayErrorDTO gatewayErrorDTO){
        this.gatewayErrorDTO = gatewayErrorDTO;
    }

    public GatewayErrorDTO getGatewayError() {
        return gatewayErrorDTO;
    }
}
