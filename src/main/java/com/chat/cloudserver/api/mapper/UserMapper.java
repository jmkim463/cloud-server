package com.chat.cloudserver.api.mapper;

import com.chat.cloudserver.api.dto.UserDTO;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

    @Select("SELECT COUNT(*) FROM user WHERE id = #{id}")
    Integer countSameID(@Param("id") String id);

    @Select("SELECT no FROM user WHERE id = #{id} AND password = #{password}")
    Long findUserByIDAndPassword(@Param("id") String id, @Param("password") String password);

    @Select("SELECT * FROM user WHERE no = #{no}")
    UserDTO findUserByNo(@Param("no") Long no);

    @Insert("INSERT INTO user (name, id, password, email) VALUES (#{name}, #{id}, #{password}, #{email});")
    @Options(useGeneratedKeys = true, keyProperty = "no")
    Long save(UserDTO user);

    @Update("UPDATE user SET name = #{name}, password = #{password}, email = #{email} WHERE (no = #{no});\n")
    @Options(useGeneratedKeys = true, keyProperty = "no")
    Long update(UserDTO userDTO);

}
