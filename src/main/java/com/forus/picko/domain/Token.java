package com.forus.picko.domain;

import lombok.Getter;

@Getter
public class Token {
    private String accessToken;
    private String refreshToken;
    private String tokenType;

    public Token(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.tokenType = "Bearer";
    }
}
