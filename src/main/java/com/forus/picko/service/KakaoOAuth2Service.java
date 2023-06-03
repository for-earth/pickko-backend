package com.forus.picko.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.forus.picko.domain.Token;
import com.forus.picko.dto.KakaoUserDto;
import com.forus.picko.dto.KakaoProfileDto;
import com.forus.picko.dto.KakaoTokenDto;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class KakaoOAuth2Service {

    private String clientId = "5951cda86711e0b956d5a0778a32a432";

    public Token getToken(String code, String redirectOrigin) throws JsonProcessingException {
        // HTTP Header 생성
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        // HTTP Body 생성
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "authorization_code");
        body.add("client_id", clientId);
        body.add("redirect_uri", redirectOrigin + "/oauth/kakao/callback");
        body.add("code", code);

        // HTTP 요청 보내기
        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(body, headers);
        RestTemplate rt = new RestTemplate();
        ResponseEntity<String> response = rt.exchange(
                "https://kauth.kakao.com/oauth/token",
                HttpMethod.POST,
                kakaoTokenRequest,
                String.class
        );

        // HTTP 응답 (JSON) -> 액세스 토큰 파싱
        String responseBody = response.getBody();

        ObjectMapper objectMapper = new ObjectMapper();
        KakaoTokenDto kakaoToken = null;

        System.out.println(responseBody);

        try {
            kakaoToken = objectMapper.readValue(responseBody, KakaoTokenDto.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return new Token(kakaoToken.access_token, kakaoToken.refresh_token.orElseThrow(()-> new IllegalArgumentException("null of refresh_token")));
    }

    public Token reissueToken(String refreshToken) throws JsonProcessingException {
        // HTTP Header 생성
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        // HTTP Body 생성
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "refresh_token");
        body.add("client_id", clientId);
        body.add("refresh_token", refreshToken);

        // HTTP 요청 보내기
        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(body, headers);
        RestTemplate rt = new RestTemplate();
        ResponseEntity<String> response = rt.exchange(
                "https://kauth.kakao.com/oauth/token",
                HttpMethod.POST,
                kakaoTokenRequest,
                String.class
        );

        // HTTP 응답 (JSON) -> 액세스 토큰 파싱
        String responseBody = response.getBody();

        ObjectMapper objectMapper = new ObjectMapper();
        KakaoTokenDto kakaoToken = null;

        System.out.println(responseBody.toString());

        try {
            kakaoToken = objectMapper.readValue(responseBody, KakaoTokenDto.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        System.out.println(kakaoToken.toString());

        return new Token(kakaoToken.access_token, kakaoToken.refresh_token.orElse(refreshToken) );
    }

    public KakaoUserDto getUserInfo(String accessToken) throws JsonProcessingException {
        // HTTP Header 생성
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        // HTTP 요청 보내기
        HttpEntity<MultiValueMap<String, String>> kakaoUserInfoRequest = new HttpEntity<>(headers);
        RestTemplate rt = new RestTemplate();
        ResponseEntity<String> response = rt.exchange(
                "https://kapi.kakao.com/v2/user/me",
                HttpMethod.POST,
                kakaoUserInfoRequest,
                String.class
        );

        // responseBody에 있는 정보를 꺼냄
        String responseBody = response.getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        KakaoProfileDto kakaoProfile = null;

        try {
            kakaoProfile = objectMapper.readValue(responseBody, KakaoProfileDto.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        System.out.println(kakaoProfile.toString());

        return new KakaoUserDto(kakaoProfile.id, kakaoProfile.properties.nickname, kakaoProfile.properties.profile_image, kakaoProfile.kakao_account.email);
    }
}
