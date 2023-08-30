package com.chat.cloudserver.api.mapper;

import com.chat.cloudserver.api.dto.ChatRoomDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ChatRoomMapper {

    @Insert("INSERT INTO chatroom (name, create_at) VALUES (#{name}, SYSDATE());")
    @Options(useGeneratedKeys = true, keyProperty = "no")
    Long save(ChatRoomDTO chatRoomDTO);

    @Select("SELECT A.no, A.name, A.create_at, A.user_name_list, A.user_id_list, A.last_chat, A.last_at\n" +
            "FROM (\n" +
            "SELECT room.no, if(room.name is null, (SELECT (SELECT name FROM user u WHERE u.no = p.user_no) as name FROM participant p WHERE p.chatroom_no = room.no and p.user_no != #{userNo}), room.name) as name, room.create_at,\n" +
            "(SELECT content FROM message msg WHERE msg.chatroom_no = room.no ORDER BY send_at DESC limit 1) as last_chat,\n" +
            "(SELECT GROUP_CONCAT(u.name) FROM participant p2 JOIN user u ON p2.user_no = u.no WHERE p2.user_no != 1 AND p2.chatroom_no = room.no) as user_name_list,\n" +
            "(SELECT GROUP_CONCAT(u.id) FROM participant p2 JOIN user u ON p2.user_no = u.no WHERE p2.user_no != 1 AND p2.chatroom_no = room.no) as user_id_list,\n" +
            "(SELECT send_at FROM message msg WHERE msg.chatroom_no = room.no ORDER BY send_at DESC limit 1) as last_at\n" +
            "FROM chatroom room \n" +
            "WHERE no IN (SELECT chatroom_no FROM participant WHERE user_no = #{userNo})) A\n" +
            "ORDER BY A.last_at DESC, A.create_at DESC, A.no ASC")
    @Results({
            @Result(property = "no", column = "no"),
            @Result(property = "name", column = "name"),
            @Result(property = "createAt", column = "create_at"),
            @Result(property = "lastChat", column = "last_chat"),
            @Result(property = "userIdList", column = "user_id_list"),
            @Result(property = "userNameList", column = "user_name_list"),
            @Result(property = "lastAt", column = "last_at")
    })
    List<ChatRoomDTO> selectUserChatRoomList(@Param("userNo") Long userNo);

    @Select("SELECT c.no from chatroom c LEFT JOIN participant p ON c.no = p.chatroom_no\n" +
            "WHERE (p.user_no = #{userNo1} or p.user_no = #{userNo2}) AND c.name IS NULL GROUP BY c.no HAVING COUNT(*) = 2")
    Long selectPrivateChatroom(@Param("userNo1") Long userNo1, @Param("userNo2") Long userNo2);

    @Select("SELECT * FROM chatroom WHERE no = ${chatroomNo}")
    ChatRoomDTO findByNo(@Param("chatroomNo") Long chatroomNo);

}
