package com.chat.cloudserver.api.controller;

import com.chat.cloudserver.api.dto.UserDTO;
import com.chat.cloudserver.api.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @GetMapping("/login")
    public ResponseEntity<?> login(@RequestParam String id, @RequestParam String password) {
        String userNo = service.retrieveUserNoByIdAndPassword(id, password);

        UserDTO userDTO = service.

        return ResponseEntity.ok(userDTO);
    }

    @GetMapping("/check")
    public ResponseEntity<?> isHaveSameID(@RequestParam String id) {
        boolean isHaveSameID = service.isHaveSameID(id);

        return ResponseEntity.ok(isHaveSameID);
    }

    @PostMapping("/account")
    public ResponseEntity<?> account(@RequestBody UserDTO userDTO) {
        service.createUserAccount(userDTO);

        return ResponseEntity.ok("회원가입 성공");
    }
}
