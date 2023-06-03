package com.forus.picko.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.forus.picko.dto.KakaoUserDto;
import com.forus.picko.service.KakaoOAuth2Service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

@Tag(name = "user", description = "User API")
@RestController
@RequestMapping("/api/user")
public class UserController {

    private static KakaoOAuth2Service kakaoOAuth2Service;

    @Autowired
    public UserController(KakaoOAuth2Service kakaoOAuth2Service) {
        this.kakaoOAuth2Service = kakaoOAuth2Service;
    }

    @Operation(summary = "profile", description = "유저 프로필")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = KakaoUserDto.class))),
    })
    @GetMapping("/profile")
    @ResponseBody
    public ResponseEntity<KakaoUserDto> user(@RequestHeader String authorization) throws JsonProcessingException, HttpClientErrorException {
        String[] split = authorization.split(" ");
        String accessToken = split[1];

        try {
            return ResponseEntity.ok(kakaoOAuth2Service.getUserInfo(accessToken));
        } catch (HttpClientErrorException e) {
            return new ResponseEntity(e.getMessage(), e.getStatusCode());
        }
    }
}
