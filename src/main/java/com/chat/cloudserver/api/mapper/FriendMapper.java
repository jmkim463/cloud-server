package com.chat.cloudserver.api.mapper;

import com.chat.cloudserver.api.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Mapper
public interface FriendMapper {

    @Select("SELECT u.no, u.name, u.id, u.password, u.email FROM (" +
            "SELECT * FROM friend WHERE status = #{status} AND (user_no_1 = #{userNo} or user_no_2 = #{userNo})) A " +
            "JOIN user u ON u.no = if(user_no_1 = #{userNo}, user_no_2, user_no_1)")
    List<UserDTO> selectFriendList(@Param("status") int status, @Param("userNo") Long userNo);

}
