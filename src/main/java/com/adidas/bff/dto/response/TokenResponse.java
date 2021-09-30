package com.adidas.bff.dto.response;

public class TokenResponse {

    private String token;

    public TokenResponse(String token) {
        this.token = "Bearer " + token;
    }

    public String getToken() {
        return token;
    }
}
