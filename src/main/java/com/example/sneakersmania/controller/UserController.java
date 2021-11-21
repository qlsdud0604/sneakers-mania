package com.example.sneakersmania.controller;

import com.example.sneakersmania.model.KakaoProfile;
import com.example.sneakersmania.model.OAuthToken;
import com.example.sneakersmania.model.User;
import com.example.sneakersmania.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Controller
public class UserController {

    @Value("${password.key}")
    private String passwordKey;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/auth/joinForm")
    public String joinForm() {
        return "user/joinForm";
    }

    @GetMapping("/auth/loginForm")
    public String loginForm() {
        return "user/loginForm";
    }

    @GetMapping("/auth/kakao/callback")
    public String kakaoCallback(String code) throws JsonProcessingException {

        // ==================== 사용자 Access Token 요청 ====================
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

        /* Access Token을 자바 객체로 매핑 */
        ObjectMapper objectMapper = new ObjectMapper();
        OAuthToken oAuthToken = objectMapper.readValue(response.getBody(), OAuthToken.class);


        // ==================== Access Token을 이용한 사용자 정보 요청 ====================
        RestTemplate rt02 = new RestTemplate();

        /* HttpHeader 오브젝트 생성 */
        HttpHeaders headers02 = new HttpHeaders();
        headers02.add("Authorization", "Bearer " + oAuthToken.getAccess_token());
        headers02.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        /* HttpHeader와 HttpBody를 하나의 오브젝트로 담기 */
        HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest =
                new HttpEntity<>(headers02);

        /* Post 방식의 Http 요청 */
        ResponseEntity<String> response02 = rt02.exchange(
                "https://kapi.kakao.com/v2/user/me",   // 요청 주소
                HttpMethod.POST,   // method 방식
                kakaoProfileRequest,   // 전달 데이터
                String.class   // 응답받을 데이터 타입
        );

        /* 사용자 프로필 정보를 자바 객체로 매핑 */
        ObjectMapper objectMapper02 = new ObjectMapper();
        KakaoProfile kakaoProfile = objectMapper02.readValue(response02.getBody(), KakaoProfile.class);


        // ==================== 사용자 정보를 통한 회원가입 진행 ====================
        User kakaoUser = User.builder()
                .username(kakaoProfile.getKakao_account().getEmail() + "_" + kakaoProfile.getId())
                .password(passwordKey)
                .oauth("kakao")
                .build();

        User originUser = userService.getUser(kakaoUser.getUsername());

        /* 비 가입자인 경우 */
        if (originUser.getUsername() == null)
            userService.join(kakaoUser);

        /* 로그인 처리 */
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(kakaoUser.getUsername(), passwordKey));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return "redirect:/";
    }

    @GetMapping("/user/profileForm")
    public String profileForm() {
        return "user/profileForm";
    }

    @GetMapping("/user/updateForm")
    public String updateForm() {
        return "user/updateForm";
    }
}

// 1. 인증이 안된 사용자들이 출입할 수 있는 경로를 "/auth"로 설정하기 위해 회원가입, 로그인 url에 "/auth" 추가