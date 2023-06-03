package com.forus.picko.dto;

import lombok.Data;
import lombok.ToString;

import java.util.Optional;

@Data
@ToString
public class MemberUpdateRequestDto {
    private Optional<String> nickname = Optional.empty();
    private Optional<String> email = Optional.empty();
    private Optional<String> profileImage = Optional.empty();
    private Optional<Long> companyId = Optional.empty();
    private Optional<Long> jobPositionId = Optional.empty();
}
