package com.example.sneakersmania.controller.api;

import com.example.sneakersmania.config.auth.PrincipalDetails;
import com.example.sneakersmania.dto.ResponseDto;
import com.example.sneakersmania.model.Board;
import com.example.sneakersmania.model.User;
import com.example.sneakersmania.service.BoardService;
import com.example.sneakersmania.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class BoardApiController {

    @Autowired
    private BoardService boardService;


    @PostMapping("/api/board")
    public ResponseDto<Integer> save(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetails principal) {
        boardService.save(board, principal.getUser());

        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }


}
