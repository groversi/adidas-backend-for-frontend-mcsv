package com.adidas.bff.controllers;

import com.adidas.bff.dto.response.TokenResponse;
import com.adidas.bff.services.AuthenticationService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1")
public class AuthenticationController {

    private static final Logger log = LoggerFactory.getLogger(AuthenticationController.class);

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping(value = "/auth")
    @ApiOperation(value = "Generate jws")
    public ResponseEntity<TokenResponse> getSubscriptionById() {

        log.info("Generate token request received");
        TokenResponse tokenResponse = authenticationService.generateToken();
        return new ResponseEntity<>(tokenResponse, HttpStatus.OK);
    }
}
