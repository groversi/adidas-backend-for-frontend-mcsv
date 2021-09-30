package com.adidas.bff.exceptions.dto;

import org.slf4j.MDC;
import org.springframework.http.HttpStatus;

import java.time.Instant;

public class GatewayErrorDTO {

    private String timestamp = Instant.now().toString();
    private Integer status;
    private String error;
    private String message;
    private String traceId = MDC.get("traceId");


    public GatewayErrorDTO(HttpStatus status, String error, String message) {
        this.status = status.value();
        this.error = error;
        this.message = message;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public Integer getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public String getTraceId() {
        return traceId;
    }
}

