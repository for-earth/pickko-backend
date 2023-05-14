package com.forus.picko.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
public class User {
    public User(Long id, String nickname, String profileImage, String thumbnailImage) {
        this.id = id;
        this.nickname = nickname;
        this.profileImage = profileImage;
        this.thumbnailImage = thumbnailImage;
    }

    private Long id;
    private String nickname;
    private String profileImage;
    private String thumbnailImage;

}
