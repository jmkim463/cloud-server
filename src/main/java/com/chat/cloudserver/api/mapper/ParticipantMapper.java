package com.chat.cloudserver.api.mapper;

import com.chat.cloudserver.api.dto.ChatRoomDTO;
import com.chat.cloudserver.api.dto.ParticipantDTO;
import com.chat.cloudserver.api.dto.UserDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ParticipantMapper {

    @Insert("INSERT INTO participant (user_no, chatroom_no, join_at) VALUES (#{userNo}, #{chatroomNo}, NOW());")
    @Options(useGeneratedKeys = true, keyProperty = "no")
    Long save(ParticipantDTO participantDTO);

    @Select("SELECT user_no FROM participant WHERE user_no != #{userNo} AND chatroom_no = #{chatroomNo}")
    List<Long> selectOtherParticipantsInChatRoom(@Param("userNo") Long userNo, @Param("chatroomNo") Long chatroomNo);
}
