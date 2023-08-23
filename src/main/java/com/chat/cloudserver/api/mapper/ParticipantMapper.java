package com.chat.cloudserver.api.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ParticipantMapper {

    @Select("SELECT * FROM participant WHERE user_no != #{userNo} AND chatroom_no = #{chatroomNo}")
    List<Long> selectOtherParticipantsInChatRoom(@Param("userNo") Long userNo, @Param("chatroomNo") Long chatroomNo);
}
