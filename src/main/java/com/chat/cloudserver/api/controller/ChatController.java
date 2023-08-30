package com.chat.cloudserver.api.controller;

import com.chat.cloudserver.api.dto.ChatRoomDTO;
import com.chat.cloudserver.api.dto.MessageDTO;
import com.chat.cloudserver.api.dto.UserDTO;
import com.chat.cloudserver.api.service.ChatRoomService;
import com.chat.cloudserver.api.service.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class ChatController {

    private final ChatRoomService chatRoomService;

    private final MessageService messageService;

    @GetMapping("/user/chatroom")
    public ResponseEntity<?> getUserChatRoomList(@RequestParam("userNo") Long userNo) {
        List<ChatRoomDTO> chatRoomDTOList = chatRoomService.selectUserChatRoom(userNo);

        if(chatRoomDTOList == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("");
        }

        return ResponseEntity.ok(chatRoomDTOList);
    }

    @GetMapping("/chatroom/massage")
    public ResponseEntity<?> getMessageOfChatRoomList(@RequestParam("chatroomNo") Long chatroomNo) {
        List<MessageDTO> messageDTOList = messageService.getChatroomOfMessageList(chatroomNo);


        return ResponseEntity.ok(messageDTOList);
    }

    @GetMapping("/chatroom")
    public ResponseEntity<?> getPrivateChatRoom(@RequestParam("userNo1") Long userNo1, @RequestParam("userNo2") Long userNo2) {
        ChatRoomDTO chatRoomDTO = chatRoomService.getPrivateChatroom(userNo1, userNo2);

        return ResponseEntity.ok(chatRoomDTO);
    }

}
