package com.example.sneakersmania.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerTest {

    @GetMapping("/test/hello")
    public String hello() {
        return "hello spring boot";
    }
}
