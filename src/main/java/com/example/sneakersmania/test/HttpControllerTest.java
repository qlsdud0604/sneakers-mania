package com.example.sneakersmania.test;

import org.springframework.web.bind.annotation.*;

@RestController
public class HttpControllerTest {

    @GetMapping("/http/get")
    public String getTest(Member member) {
        return "get 요청 " + member.getId() + ", " + member.getUsername();
    }

    @PostMapping("http/post")
    public String postTest(@RequestBody Member member) {
        return "post 요청 " + member.getId() + ", " + member.getUsername();
    }

    @PutMapping("http/put")
    public String putTest(@RequestBody Member member) {
        return "put 요청 " + member.getId() + ", " + member.getUsername();
    }

    @DeleteMapping("http/delete")
    public String deleteTest() {
        return "delete 요청";
    }
}

// 1. "@RestController" : 사용자 요청에 대해 데이터를 응답
// 2. "@Controller" : 사용자 요청에 대해 HTML 파일을 응답
