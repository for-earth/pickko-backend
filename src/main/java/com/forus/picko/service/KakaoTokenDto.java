package com.forus.picko.service;

import lombok.Data;
import lombok.ToString;

import java.util.Optional;

@ToString
@Data
public class KakaoTokenDto {

    public String token_type;
    public String access_token;
    public String id_token;
    public Long expires_in;
    public String refresh_token;
    public Long refresh_token_expires_in;
    public String scope;
}
