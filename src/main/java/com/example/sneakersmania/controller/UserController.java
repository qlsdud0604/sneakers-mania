package com.example.sneakersmania.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
public class UserController {

    @GetMapping("/auth/joinForm")
    public String joinForm() {
        return "user/joinForm";
    }

    @GetMapping("/auth/loginForm")
    public String loginForm() {
        return "user/loginForm";
    }

    @ResponseBody
    @GetMapping("/auth/kakao/callback")
    public String kakaoCallback(String code) {

        RestTemplate rt = new RestTemplate();

        /* HttpHeader 오브젝트 생성 */
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        /* HttpBody 오브젝트 생성 */
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", "9cbe170d83aa45724a840169b89614f6");
        params.add("redirect_uri", "http://localhost:8000/auth/kakao/callback");
        params.add("code", code);

        /* HttpHeader와 HttpBody를 하나의 오브젝트로 담기 */
        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest =
                new HttpEntity<>(params, headers);

        /* Post 방식의 Http 요청 */
        ResponseEntity<String> response = rt.exchange(
                "https://kauth.kakao.com/oauth/token",   // 요청 주소
                HttpMethod.POST,   // method 방식
                kakaoTokenRequest,   // 전달 데이터
                String.class   // 응답받을 데이터 타입
        );

        return "카카오 액세스 토큰 :  " + response;
    }

    @GetMapping("/user/updateForm")
    public String updateForm() {
        return "user/updateForm";
    }
}

// 1. 인증이 안된 사용자들이 출입할 수 있는 경로를 "/auth"로 설정하기 위해 회원가입, 로그인 url에 "/auth" 추가