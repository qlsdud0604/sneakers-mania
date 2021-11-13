package com.example.sneakersmania.controller.api;

import com.example.sneakersmania.dto.ResponseDto;
import com.example.sneakersmania.model.RoleType;
import com.example.sneakersmania.model.User;
import com.example.sneakersmania.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class UserApiController {

    @Autowired
    private UserService userService;

    @PostMapping("/api/user")
    public ResponseDto<Integer> save(@RequestBody User user) {
        user.setRole(RoleType.USER);
        userService.join(user);

        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

//    /** 전통적인 방식의 로그인 */
//    @PostMapping("/api/user/login")
//    public ResponseDto<Integer> login(@RequestBody User user, HttpSession session) {
//        User principal = userService.login(user);
//
//        if (principal != null) {
//            session.setAttribute("principal", principal);
//        }
//        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
//    }
}
