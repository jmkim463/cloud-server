package com.chat.cloudserver.api.service;

import com.chat.cloudserver.api.mapper.ParticipantMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ParticipantService {

    private final ParticipantMapper mapper;

    public List<Long> getOtherParticipantsInChatRoom(Long userNo, Long chatroomNo) {
        List<Long> list = mapper.selectOtherParticipantsInChatRoom(userNo, chatroomNo);
        return list;
    }
}
