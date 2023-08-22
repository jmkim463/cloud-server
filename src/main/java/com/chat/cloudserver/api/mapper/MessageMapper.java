package com.chat.cloudserver.api.mapper;

import com.chat.cloudserver.api.dto.MessageDTO;
import org.apache.ibatis.annotations.*;

@Mapper
public interface MessageMapper {

    @Insert("INSERT INTO message (chatroom_no, sender_no, content, send_at) VALUES (#{chatroomNo}, #{senderNo}, #{content}, #{sendAt});")
    @Options(useGeneratedKeys = true, keyProperty = "no")
    Long save(@Param("message")MessageDTO messageDTO);
}
