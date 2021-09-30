package com.adidas.bff.services;

import com.adidas.bff.dto.request.TokenRequest;
import com.adidas.bff.dto.response.TokenResponse;

public interface AuthenticationService {

    TokenResponse generateToken();

    Boolean validateJwtToken(String jwtToken);
}
