package com.chat.cloudserver.api.controller;

import com.chat.cloudserver.api.dto.ChatRoomDTO;
import com.chat.cloudserver.api.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class ChatController {

    private final ChatRoomService chatRoomService;

    @GetMapping("/user/chatroom")
    public ResponseEntity<?> selectUserChatRoomList(@RequestParam("userNo") Long userNo) {
        List<ChatRoomDTO> chatRoomDTOList = chatRoomService.selectUserChatRoom(userNo);

        if(chatRoomDTOList == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("");
        }

        return ResponseEntity.ok(chatRoomDTOList);
    }
}
