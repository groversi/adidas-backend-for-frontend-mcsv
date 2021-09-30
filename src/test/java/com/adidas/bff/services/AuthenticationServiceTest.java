package com.adidas.bff.services;

import com.adidas.bff.dto.response.TokenResponse;
import com.adidas.bff.services.impl.AuthenticationServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AuthenticationServiceTest {

    @InjectMocks
    private AuthenticationServiceImpl authenticationService;

    @Test
    public void testShouldRefuseAnInvalidToken() {
        Assert.assertFalse(authenticationService.validateJwtToken("someToken"));
    }

    @Test
    public void testShouldRefuseExpiredToken() throws InterruptedException {
        TokenResponse token = authenticationService.generateToken();
        authenticationService.validateJwtToken(token.getToken());
        Thread.sleep(31000);

        Assert.assertFalse(authenticationService.validateJwtToken(token.getToken()));
    }

    @Test
    public void testShouldGenerateValidToken() {
        TokenResponse token = authenticationService.generateToken();
        authenticationService.validateJwtToken(token.getToken());

        Assert.assertTrue(authenticationService.validateJwtToken(token.getToken()));
    }



}
