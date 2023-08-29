package com.chat.cloudserver.api.mapper;

import com.chat.cloudserver.api.dto.FriendDTO;
import com.chat.cloudserver.api.dto.UserDTO;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Mapper
public interface FriendMapper {

    @Select("SELECT u.no, u.name, u.id, u.password, u.email FROM (\n" +
            "SELECT * FROM friend WHERE status = 2 AND (user_no_1 = #{userNo} or user_no_2 = #{userNo})) A \n" +
            "JOIN user u ON u.no = if(user_no_1 = #{userNo}, user_no_2, user_no_1)")
    List<UserDTO> selectFriendList(@Param("userNo") Long userNo);

    @Select("SELECT u.no, u.name, u.id, u.password, u.email FROM (\n" +
            "SELECT * FROM friend WHERE status = 1 AND user_no_2 = #{userNo}) A \n" +
            "JOIN user u ON u.no = user_no_1")
    List<UserDTO> selectPendingFriendList(@Param("userNo") Long userNo);

    @Select("SELECT * FROM user WHERE user.no != #{userNo} AND (id LIKE CONCAT('%', #{keyword}, '%') OR email LIKE CONCAT('%', #{keyword}, '%')) AND " +
            "(SELECT COUNT(*) FROM friend WHERE (user_no_1 = #{userNo} AND user_no_2 = user.no) OR (user_no_1 = user.no AND user_no_2 = #{userNo})) = 0")
    List<UserDTO> selectNotFriendUserList(@Param("keyword") String keyword, @Param("userNo") Long userNo);

    @Insert("INSERT INTO friend (user_no_1, user_no_2, status) VALUES (#{userNo1}, #{userNo2}, '1');")
    @Options(useGeneratedKeys = true, keyProperty = "no")
    Long save(FriendDTO friendDTO);

    @Update("UPDATE friend SET status = #{status} WHERE (user_no_1 = #{userNo1} AND user_no_2 = #{userNo2})")
    void updateStatus(@Param("userNo1") Long userNo1, @Param("userNo2") Long userNo2, @Param("status") int status);
    
}
