package com.chat.cloudserver.api.mapper;

import com.chat.cloudserver.api.dto.ChatRoomDTO;
import com.chat.cloudserver.api.dto.UserDTO;
import org.apache.catalina.User;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Mapper
public interface ChatRoomMapper {

    @Select("SELECT A.no, A.name, A.create_at, A.last_chat_at\n" +
            "FROM (SELECT room.no, room.name, room.create_at, (SELECT send_at FROM message WHERE chatroom_no = no ORDER BY send_at DESC) AS last_chat_at\n" +
            "\tFROM chatroom room WHERE no IN (SELECT chatroom_no FROM participant WHERE user_no = 1)) AS A ORDER BY last_chat_at DESC")
    @Results({
            @Result(property = "no", column = "no"),
            @Result(property = "name", column = "name"),
            @Result(property = "createAt", column = "create_at"),
            @Result(property = "lastChatAt", column = "last_chat_at")
    })
    List<ChatRoomDTO> selectUserChatRoomList(@Param("userNo") Long userNo);

}
