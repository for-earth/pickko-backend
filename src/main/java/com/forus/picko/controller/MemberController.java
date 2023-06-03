package com.forus.picko.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.forus.picko.domain.AuthType;
import com.forus.picko.dto.KakaoUserDto;
import com.forus.picko.dto.MemberUpdateRequestDto;
import com.forus.picko.entity.Company;
import com.forus.picko.entity.Member;
import com.forus.picko.entity.JobPosition;
import com.forus.picko.service.CompanyService;
import com.forus.picko.service.KakaoOAuth2Service;
import com.forus.picko.service.MemberService;
import com.forus.picko.service.JobPositionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Optional;

@Tag(name = "member", description = "Member API")
@RestController
@RequestMapping("/api/member")
public class MemberController {
    private static KakaoOAuth2Service kakaoOAuth2Service;
    private static MemberService memberService;
    private static CompanyService companyService;
    private static JobPositionService jobPositionService;

    @Autowired
    public MemberController(KakaoOAuth2Service kakaoOAuth2Service, MemberService memberService, CompanyService companyService, JobPositionService jobPositionService) {
        this.kakaoOAuth2Service = kakaoOAuth2Service;
        this.memberService = memberService;
        this.companyService = companyService;
        this.jobPositionService = jobPositionService;
    }

    @Operation(summary = "info", description = "회원 정보")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = Member.class))),
            @ApiResponse(responseCode = "401", description = "INVALID TOKEN")
    })
    @GetMapping
    @ResponseBody
    public ResponseEntity<Member> member(@RequestHeader String authorization) throws JsonProcessingException, HttpClientErrorException {
        String[] split = authorization.split(" ");
        String accessToken = split[1];

        try {
            KakaoUserDto kakaoUser = kakaoOAuth2Service.getUserInfo(accessToken);
            Member member = new Member();
            member.setAuthType(AuthType.KAKAO);
            member.setKakaoId(kakaoUser.getId());
            member.setProfileImage(kakaoUser.getProfileImage());
            member.setName(kakaoUser.getNickname());

            return ResponseEntity.ok(memberService.join(member));
        } catch (HttpClientErrorException e) {
            return new ResponseEntity(e.getMessage(), e.getStatusCode());
        }
    }

    @Operation(summary = "update", description = "회원 정보 업데이트")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = Member.class))),
            @ApiResponse(responseCode = "401", description = "INVALID TOKEN")
    })
    @PutMapping
    @ResponseBody
    public ResponseEntity<Member> updateMember(@RequestHeader String authorization, @RequestBody @Nullable MemberUpdateRequestDto request) throws JsonProcessingException, HttpClientErrorException {
        String[] split = authorization.split(" ");
        String accessToken = split[1];

        Optional<String> nickname = request.getNickname();
        Optional<String> email = request.getEmail();
        Optional<String> profileImage = request.getProfileImage();
        Optional<Long> companyId = request.getCompanyId();
        Optional<Long> jobPositionId = request.getJobPositionId();

        try {
            KakaoUserDto kakaoUser = kakaoOAuth2Service.getUserInfo(accessToken);
            Optional<Member> member = memberService.findMemberByKakaoId(kakaoUser.getId());

            if (member.isEmpty()) throw new HttpClientErrorException(HttpStatusCode.valueOf(404), "Not Found");

            Optional<Company> company = Optional.ofNullable(companyId)
                    .flatMap(id -> id.map(companyService::findOne))
                    .orElse(Optional.empty());
            Optional<JobPosition> jobPosition = Optional.ofNullable(jobPositionId)
                    .flatMap(id -> id.map(jobPositionService::findOne))
                    .orElse(Optional.empty());

            Member updatedMember = memberService.update(member.get().getId(), nickname, email, company, profileImage, jobPosition);

            return ResponseEntity.ok(updatedMember);
        } catch (HttpClientErrorException e) {
            return new ResponseEntity(e.getMessage(), e.getStatusCode());
        }
    }
}
