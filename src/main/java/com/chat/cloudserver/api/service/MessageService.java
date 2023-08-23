package com.chat.cloudserver.api.service;

import com.chat.cloudserver.api.dto.MessageDTO;
import com.chat.cloudserver.api.mapper.MessageMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageMapper messageMapper;

    public MessageDTO save(MessageDTO messageDTO) {
        messageDTO.setSendAt(MessageDTO.getNowDateTime());

        Long no = messageMapper.save(messageDTO);
        messageDTO.setNo(no);
        return messageDTO;
    }

    public List<MessageDTO> getChatroomOfMessageList(Long chatroomNo) {
        List<MessageDTO> list = messageMapper.selectChatroomOfMessageList(chatroomNo);

        return list;
    }
}
