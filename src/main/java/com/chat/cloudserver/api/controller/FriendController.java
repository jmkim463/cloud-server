package com.chat.cloudserver.api.controller;

import com.chat.cloudserver.api.dto.UserDTO;
import com.chat.cloudserver.api.service.FriendService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/friend")
public class FriendController {

    private final FriendService service;

    @GetMapping("/list")
    public ResponseEntity<?> getUserFriendList(@RequestParam("status") int status, @RequestParam("userNo") Long userNo) {
        List<UserDTO> list = service.getUserFriendList(status, userNo);

        return ResponseEntity.ok(list);
    }

}
