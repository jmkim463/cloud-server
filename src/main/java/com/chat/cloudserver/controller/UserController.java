package com.chat.cloudserver.controller;

import com.chat.cloudserver.dto.UserDTO;
import com.chat.cloudserver.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/login")
    public ResponseEntity<?> login(@RequestParam String id, @RequestParam String password) {
        Integer no = userService.checkLogin(id, password);

        UserDTO userDTO = userService.getUserDTO(no);

        return ResponseEntity.ok(userDTO);
    }

    @GetMapping("/check/{id}")
    public ResponseEntity<?> isHaveSameID(@PathVariable String id) {
        boolean isHaveSameID = userService.isHaveSameID(id);

        return ResponseEntity.ok(isHaveSameID);
    }

    @PostMapping("/account")
    public ResponseEntity<?> account(@RequestBody UserDTO userDTO) {
        userService.createUserAccount(userDTO);

        log.info("회원가입 성공");
        log.info("no = {}, name = {}", userDTO.getNo(), userDTO.getName());

        return ResponseEntity.ok("회원가입 성공");
    }
}
