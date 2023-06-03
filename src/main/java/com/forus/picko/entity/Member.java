package com.forus.picko.entity;

import com.forus.picko.domain.AuthType;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "MEMBER")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private AuthType authType;

    @Nullable
    private Long kakaoId;

    private String name;

    @Nullable
    private String email;

    @Nullable
    private String nickname;

    private String profileImage;

    @ManyToOne
    @Nullable
    private Company company;

    @ManyToOne
    @Nullable
    private JobPosition jobPosition;
}
