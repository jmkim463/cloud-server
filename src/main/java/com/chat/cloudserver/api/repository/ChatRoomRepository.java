package com.chat.cloudserver.api.repository;

import com.chat.cloudserver.api.dto.ChatRoomDTO;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class ChatRoomRepository {

    private Map<String, ChatRoomDTO> chatRoomMap;

    @PostConstruct
    private void init() {
        chatRoomMap = new HashMap<>();
    }


}
