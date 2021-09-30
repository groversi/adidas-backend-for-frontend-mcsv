package com.adidas.bff.exceptions.dto;

import org.slf4j.MDC;
import org.springframework.http.HttpStatus;

import java.time.Instant;

public class OpenCircuitBreakDTO {

    private String timestamp = Instant.now().toString();
    private Integer status = HttpStatus.SERVICE_UNAVAILABLE.value();
    private String error = HttpStatus.SERVICE_UNAVAILABLE.getReasonPhrase();
    private String message = "Sorry for slowing down your shopping experience. " +
            "Our site is down for maintenance and we need a minute to walk it off." +
            "We will be up and running shortly!" ;
    private String traceId = MDC.get("traceId");



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

