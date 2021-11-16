package com.example.sneakersmania.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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

    @GetMapping("/user/updateForm")
    public String updateForm() {
        return "user/updateForm";
    }
}

// 1. 인증이 안된 사용자들이 출입할 수 있는 경로를 "/auth"로 설정하기 위해 회원가입, 로그인 url에 "/auth" 추가