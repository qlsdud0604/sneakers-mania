package com.example.sneakersmania.controller;

import com.example.sneakersmania.config.auth.PrincipalDetails;
import com.example.sneakersmania.model.Board;
import com.example.sneakersmania.model.KakaoProfile;
import com.example.sneakersmania.model.OAuthToken;
import com.example.sneakersmania.model.User;
import com.example.sneakersmania.service.BoardService;
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
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Controller
public class UserController {

    @Value("${password.key}")
    private String passwordKey;

    @Autowired
    private UserService userService;

    @Autowired
    private BoardService boardService;

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

        // ==================== ????????? Access Token ?????? ====================
        RestTemplate rt = new RestTemplate();

        /* HttpHeader ???????????? ?????? */
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        /* HttpBody ???????????? ?????? */
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", "9cbe170d83aa45724a840169b89614f6");
        params.add("redirect_uri", "http://localhost:8000/auth/kakao/callback");
        params.add("code", code);

        /* HttpHeader??? HttpBody??? ????????? ??????????????? ?????? */
        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest =
                new HttpEntity<>(params, headers);

        /* Post ????????? Http ?????? */
        ResponseEntity<String> response = rt.exchange(
                "https://kauth.kakao.com/oauth/token",   // ?????? ??????
                HttpMethod.POST,   // method ??????
                kakaoTokenRequest,   // ?????? ?????????
                String.class   // ???????????? ????????? ??????
        );

        /* Access Token??? ?????? ????????? ?????? */
        ObjectMapper objectMapper = new ObjectMapper();
        OAuthToken oAuthToken = objectMapper.readValue(response.getBody(), OAuthToken.class);


        // ==================== Access Token??? ????????? ????????? ?????? ?????? ====================
        RestTemplate rt02 = new RestTemplate();

        /* HttpHeader ???????????? ?????? */
        HttpHeaders headers02 = new HttpHeaders();
        headers02.add("Authorization", "Bearer " + oAuthToken.getAccess_token());
        headers02.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        /* HttpHeader??? HttpBody??? ????????? ??????????????? ?????? */
        HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest =
                new HttpEntity<>(headers02);

        /* Post ????????? Http ?????? */
        ResponseEntity<String> response02 = rt02.exchange(
                "https://kapi.kakao.com/v2/user/me",   // ?????? ??????
                HttpMethod.POST,   // method ??????
                kakaoProfileRequest,   // ?????? ?????????
                String.class   // ???????????? ????????? ??????
        );

        /* ????????? ????????? ????????? ?????? ????????? ?????? */
        ObjectMapper objectMapper02 = new ObjectMapper();
        KakaoProfile kakaoProfile = objectMapper02.readValue(response02.getBody(), KakaoProfile.class);


        // ==================== ????????? ????????? ?????? ???????????? ?????? ====================
        User kakaoUser = User.builder()
                .username(kakaoProfile.getKakao_account().getEmail() + "_" + kakaoProfile.getId())
                .password(passwordKey)
                .oauth("kakao")
                .build();

        User originUser = userService.getUser(kakaoUser.getUsername());

        /* ??? ???????????? ?????? */
        if (originUser.getUsername() == null)
            userService.join(kakaoUser);

        /* ????????? ?????? */
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

    @GetMapping("/user/userBoardList")
    public String getUserBoardList(Model model, @AuthenticationPrincipal PrincipalDetails principal) {
        User user = principal.getUser();

        List<Board> list = boardService.getUserBoardList(user.getId());

        Collections.sort(list, new Comparator<Board>() {
            @Override
            public int compare(Board o1, Board o2) {
                return o2.getId() - o1.getId();
            }
        });

        model.addAttribute("boards", list);

        return "user/userBoardList";
    }
}

// 1. ????????? ?????? ??????????????? ????????? ??? ?????? ????????? "/auth"??? ???????????? ?????? ????????????, ????????? url??? "/auth" ??????