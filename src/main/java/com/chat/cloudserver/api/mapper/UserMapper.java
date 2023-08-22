package com.chat.cloudserver.api.mapper;

import com.chat.cloudserver.api.dto.UserDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("SELECT COUNT(*) FROM user WHERE id = #{id}")
    Integer countSameID(@Param("id") String id);

    @Select("SELECT no FROM user WHERE id = #{id} AND password = #{password}")
    Long findUserByIDAndPassword(@Param("id") String id, @Param("password") String password);

    @Select("SELECT * FROM user WHERE no = #{no}")
    UserDTO findUserByNo(@Param("no") Long no);

    @Insert("INSERT INTO VALUES")
    Long saveUser(@Param("user") UserDTO user);
}
