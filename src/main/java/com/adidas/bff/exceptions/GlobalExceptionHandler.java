package com.adidas.bff.exceptions;

import com.adidas.bff.exceptions.customExceptions.*;
import com.adidas.bff.exceptions.dto.GatewayErrorDTO;
import com.adidas.bff.exceptions.dto.OpenCircuitBreakDTO;
import com.netflix.hystrix.exception.HystrixRuntimeException;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    private final String INTERNAL_SERVER_ERROR_MSG = "Internal error";
    private final String METHOD_FORBIDEN_ERROR_MSG = "Sorry. You are not alowed";
    private final String METHOD_NOT_ALLOWED_ERROR_MSG = "Sorry. You are not allowed";
    private final String RESOURCE_NOT_FOUND_ERROR_MSG = ":( Didn't find any answer";
    private final String BAD_REQUEST_ERROR_MSG = "Invalid request";

    @ExceptionHandler(Exception.class)
    public ResponseEntity<GatewayErrorDTO> HandleException(Exception error){
        log.error(error.getMessage(), error);
        return buildDefaultErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, INTERNAL_SERVER_ERROR_MSG);
    }

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<GatewayErrorDTO> HandleFeignExceptionException(FeignException error) {
        log.error(error.getMessage(), error);
        return buildDefaultErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, INTERNAL_SERVER_ERROR_MSG);
    }

    @ExceptionHandler(InternalServerErrorException.class)
    public ResponseEntity<GatewayErrorDTO> HandleInternalServerErrorException(InternalServerErrorException error) {
        log.error(error.getMessage(), error);
        return buildDefaultErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, INTERNAL_SERVER_ERROR_MSG);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<GatewayErrorDTO> HandleRuntimeException(InternalServerErrorException error) {
        log.error(error.getMessage(), error);
        return buildDefaultErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, INTERNAL_SERVER_ERROR_MSG);
    }

    @ExceptionHandler(MethodForbidenException.class)
    public ResponseEntity<GatewayErrorDTO> HandleMethodForbidenException(MethodForbidenException error) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error.getGatewayErrorDTO());
    }

    @ExceptionHandler(MethodNotAllowedException.class)
    public ResponseEntity<GatewayErrorDTO> HandleMethodNotAllowedException(MethodNotAllowedException error) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error.getGatewayErrorDTO());
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<GatewayErrorDTO> HandleResourceNotFoundException(ResourceNotFoundException error) {
        return buildDefaultErrorMessage(HttpStatus.NOT_FOUND, RESOURCE_NOT_FOUND_ERROR_MSG);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<GatewayErrorDTO> HandleBadRequestException(BadRequestException error) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error.getGatewayError());
    }

    @ExceptionHandler(OpenCircuitBreakException.class)
    public ResponseEntity<OpenCircuitBreakDTO> HandleOpenCircuitBreakException(OpenCircuitBreakException error) {
        log.error(error.getMessage(), error);
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(error.getOpenCircuitBreakException());
    }

    @ExceptionHandler(HystrixRuntimeException.class)
    public ResponseEntity<?> HandleOpenCircuitBreakException(HystrixRuntimeException error) {
        log.error(error.getMessage(), error);
        if(error.getFallbackException().getCause() instanceof OpenCircuitBreakException)
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(new OpenCircuitBreakDTO());
        return buildDefaultErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, INTERNAL_SERVER_ERROR_MSG);
    }

    private ResponseEntity<GatewayErrorDTO> buildDefaultErrorMessage(HttpStatus httpStatus, String errorMessage){
        GatewayErrorDTO gatewayErrorDTO =  new GatewayErrorDTO(httpStatus, httpStatus.getReasonPhrase(), errorMessage);

        return new ResponseEntity<>(gatewayErrorDTO, httpStatus);
    }
}
