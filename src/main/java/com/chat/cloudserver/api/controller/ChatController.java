package com.chat.cloudserver.api.controller;

import com.chat.cloudserver.api.dto.ChatRoomDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    @GetMapping("/roomList")
    public List<ChatRoomDTO> getUserRoomListAll(@RequestParam String userNo) {
        List<ChatRoomDTO> list = new ArrayList<>();

        for(int i = 1; i <= 3; i++) {
            list.add(ChatRoomDTO.builder().no(String.format("ROOM-%05d", i)).build());
        }

        return list;
    }

}
