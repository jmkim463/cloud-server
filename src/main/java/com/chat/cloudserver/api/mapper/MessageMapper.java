package com.chat.cloudserver.api.mapper;

import com.chat.cloudserver.api.dto.MessageDTO;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Mapper
public interface MessageMapper {

    @Insert("INSERT INTO message (chatroom_no, sender_no, content, send_at) VALUES (#{chatroomNo}, #{senderNo}, #{content}, #{sendAt});")
    @Options(useGeneratedKeys = true, keyProperty = "no")
    Long save(MessageDTO messageDTO);

    @Select("SELECT msg.no, msg.chatroom_no, msg.sender_no, msg.content, msg.send_at, u.name as sender_name, u.id as sender_id, u.password as sender_password, u.email as sender_email\n" +
            "FROM message msg JOIN user u ON u.no = msg.sender_no WHERE msg.chatroom_no = #{chatroomNo} ORDER BY msg.send_at ASC")
    @Results({
            @Result(property = "no", column = "no"),
            @Result(property = "chatroomNo", column = "chatroom_no"),
            @Result(property = "senderNo", column = "sender_no"),
            @Result(property = "senderDTO.no", column = "sender_no"),
            @Result(property = "senderDTO.name", column = "sender_name"),
            @Result(property = "senderDTO.id", column = "sender_id"),
            @Result(property = "senderDTO.password", column = "sender_password"),
            @Result(property = "senderDTO.email", column = "sender_email"),
            @Result(property = "content", column = "content"),
            @Result(property = "sendAt", column = "send_at")
    })
    List<MessageDTO> selectChatroomOfMessageList(@Param("chatroomNo") Long chatroomNo);
}
