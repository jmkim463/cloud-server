package com.chat.cloudserver;

import com.chat.cloudserver.dto.ChatRoomDTO;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
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
