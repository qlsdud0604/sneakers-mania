package com.example.sneakersmania.controller.api;

import com.example.sneakersmania.config.auth.PrincipalDetails;
import com.example.sneakersmania.dto.ReplySaveRequestDto;
import com.example.sneakersmania.dto.ResponseDto;
import com.example.sneakersmania.model.Board;
import com.example.sneakersmania.model.Reply;
import com.example.sneakersmania.model.User;
import com.example.sneakersmania.service.BoardService;
import com.example.sneakersmania.service.UserService;
import com.google.gson.JsonObject;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;


@RestController
public class BoardApiController {

    @Autowired
    private BoardService boardService;


    @PostMapping("/api/board")
    public ResponseDto<Integer> save(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetails principal) {
        boardService.saveBoard(board, principal.getUser());

        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    @DeleteMapping("/api/board/{id}")
    public ResponseDto<Integer> deleteById(@PathVariable int id) {
        boardService.deleteBoard(id);

        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    @PutMapping("/api/board/{id}")
    public ResponseDto<Integer> update(@PathVariable int id, @RequestBody Board board) {
        boardService.updateBoard(id, board);

        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    @PostMapping("/api/board/{boardId}/reply")
    public ResponseDto<Integer> replySave(@RequestBody ReplySaveRequestDto replySaveRequestDto) {
        boardService.saveReply(replySaveRequestDto);

        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    @DeleteMapping("/api/board/{boardId}/reply/{replyId}")
    public ResponseDto<Integer> replyDelete(@PathVariable int replyId) {
        boardService.deleteReply(replyId);

        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    @PostMapping(value = "/uploadSummernoteImageFile", produces = "application/json; charset=utf8")
    public JsonObject uploadSummernoteImageFile(@RequestParam("file") MultipartFile multipartFile, HttpServletRequest request) {
        JsonObject jsonObject = new JsonObject();

        String fileRoot = "C:\\summernote_image\\";   // 저장될 외부 파일 경로
        String originalFileName = multipartFile.getOriginalFilename();   // 오리지날 파일명
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));   // 파일 확장자

        String savedFileName = UUID.randomUUID() + extension;   // 저장될 파일명

        File targetFile = new File(fileRoot + savedFileName);

        try {
            InputStream fileStream = multipartFile.getInputStream();
            FileUtils.copyInputStreamToFile(fileStream, targetFile);   // 파일 저장
            jsonObject.addProperty("url", "/summernoteImage/" + savedFileName);
            jsonObject.addProperty("responseCode", "success");

        } catch (IOException e) {
            FileUtils.deleteQuietly(targetFile);   //저장된 파일 삭제
            jsonObject.addProperty("responseCode", "error");
            e.printStackTrace();
            e.printStackTrace();
        }

        System.out.println(jsonObject);
        return jsonObject;
    }
}
