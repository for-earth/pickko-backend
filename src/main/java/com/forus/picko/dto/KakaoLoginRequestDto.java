package com.forus.picko.dto;

import com.forus.picko.domain.GrantType;
import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class KakaoLoginRequestDto {

    private String code;
    private String refreshToken;
    private GrantType grantType;
}
