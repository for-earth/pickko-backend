package com.forus.picko.dto;

import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Data;
import lombok.ToString;

import java.util.Optional;

@ToString
@Data
public class KakaoTokenDto {

    public String token_type;
    public String access_token;
    public Optional<String> id_token = Optional.empty();
    public Long expires_in;
    public Optional<String> refresh_token = Optional.empty();
    public Optional<Long> refresh_token_expires_in = Optional.empty();
    public Optional<String> scope = Optional.empty();

    @JsonSetter
    public void setIdToken(String id_token) {
        this.id_token = Optional.ofNullable(id_token);
    }

    @JsonSetter
    public void setRefresh_token(String refresh_token) {
        this.refresh_token = Optional.ofNullable(refresh_token);
    }

    @JsonSetter
    public void setRefresh_token_expires_in(Long refresh_token_expires_in) {
        this.refresh_token_expires_in = Optional.ofNullable(refresh_token_expires_in);
    }

    @JsonSetter
    public void setScope(String scope) {
        this.scope = Optional.ofNullable(scope);
    }
}
