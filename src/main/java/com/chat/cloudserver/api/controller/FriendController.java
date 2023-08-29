package com.chat.cloudserver.api.controller;

import com.chat.cloudserver.api.dto.FriendDTO;
import com.chat.cloudserver.api.dto.UserDTO;
import com.chat.cloudserver.api.service.FriendService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/friend")
public class FriendController {

    private final FriendService service;

    @GetMapping("/list")
    public ResponseEntity<?> getFriendList(@RequestParam("userNo") Long userNo) {
        List<UserDTO> list = service.getFriendList(userNo);

        return ResponseEntity.ok(list);
    }

    @GetMapping("/list/pending")
    public ResponseEntity<?> getPendingFriendList(@RequestParam("userNo") Long userNo) {
        List<UserDTO> list = service.getPendingFriendList(userNo);

        return ResponseEntity.ok(list);
    }

    @GetMapping("/list/not")
    public ResponseEntity<?> getNotFriendUserList(@RequestParam("keyword") String keyword, @RequestParam("userNo") Long userNo) {
        List<UserDTO> list = service.getNotFriendUserList(keyword, userNo);

        return ResponseEntity.ok(list);
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody FriendDTO friendDTO) {
        Long no = service.save(friendDTO);

        return ResponseEntity.ok(no);
    }

    @GetMapping("/update/status")
    public ResponseEntity<?> updateStatus(@RequestParam Long userNo1, @RequestParam Long userNo2, @RequestParam int status) {
        service.updateStatus(userNo1, userNo2, status);

        return ResponseEntity.ok("status update success");
    }
}
