package com.forus.picko.dto;

import lombok.Getter;
import lombok.ToString;

import java.util.Optional;

@Getter
@ToString
public class KakaoUserDto {
    public KakaoUserDto(Long id, String nickname, String profileImage, Optional<String> email) {
        this.id = id;
        this.nickname = nickname;
        this.profileImage = profileImage;
        this.email = email;
    }

    private Long id;
    private String nickname;
    private String profileImage;
    private Optional<String> email;
}
