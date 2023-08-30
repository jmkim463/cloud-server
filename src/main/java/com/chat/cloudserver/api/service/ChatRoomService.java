package com.chat.cloudserver.api.service;

import com.chat.cloudserver.api.dto.ChatRoomDTO;
import com.chat.cloudserver.api.dto.ParticipantDTO;
import com.chat.cloudserver.api.mapper.ChatRoomMapper;
import com.chat.cloudserver.api.mapper.ParticipantMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatRoomService {

    private final ChatRoomMapper chatRoomMapper;

    private final ParticipantMapper participantMapper;

    public List<ChatRoomDTO> selectUserChatRoom(Long userNo) {
        List<ChatRoomDTO> chatRoomDTOList = chatRoomMapper.selectUserChatRoomList(userNo);

        return chatRoomDTOList;
    }

    public ChatRoomDTO getPrivateChatroom(Long userNo1, Long userNo2) {
        Long chatroomNo = chatRoomMapper.selectPrivateChatroom(userNo1, userNo2);

        if(chatroomNo == null) {
            ChatRoomDTO chatRoomDTO = ChatRoomDTO.builder().build();
            chatRoomMapper.save(chatRoomDTO);

            chatroomNo = chatRoomDTO.getNo();

            ParticipantDTO participantDTO1 = ParticipantDTO.builder()
                    .chatroomNo(chatroomNo)
                    .userNo(userNo1)
                    .build();
            ParticipantDTO participantDTO2 = ParticipantDTO.builder()
                    .chatroomNo(chatroomNo)
                    .userNo(userNo2)
                    .build();

            participantMapper.save(participantDTO1);
            participantMapper.save(participantDTO2);
        }

        ChatRoomDTO chatRoomDTO = chatRoomMapper.findByNo(chatroomNo);

        return chatRoomDTO;
    }
}
