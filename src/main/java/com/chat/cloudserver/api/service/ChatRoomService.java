package com.chat.cloudserver.api.service;

import com.chat.cloudserver.api.dto.ChatRoomDTO;
import com.chat.cloudserver.api.mapper.ChatRoomMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ChatRoomService {

    private final ChatRoomMapper mapper;

    public ChatRoomService(ChatRoomMapper mapper) {
        this.mapper = mapper;
    }

    public List<ChatRoomDTO> selectUserChatRoom(Long userNo) {
        List<ChatRoomDTO> chatRoomDTOList = mapper.selectUserChatRoomList(userNo);

        return chatRoomDTOList;
    }

    public void getPersonalChatroom(Long userNo1, Long userNo2) {
        Long no = mapper.selectPersonalChatroom(userNo1, userNo2);

        if(no == null) {
            
        }
    }
}
