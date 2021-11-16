package com.example.sneakersmania.controller.api;

import com.example.sneakersmania.dto.ResponseDto;
import com.example.sneakersmania.model.RoleType;
import com.example.sneakersmania.model.User;
import com.example.sneakersmania.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserApiController {

    @Autowired
    private UserService userService;


    @PostMapping("/auth/joinProc")
    public ResponseDto<Integer> save(@RequestBody User user) {
        userService.join(user);

        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    @PutMapping("/user")
    public ResponseDto<Integer> update(@RequestBody User user) {
        userService.updateUser(user);

        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }


}
