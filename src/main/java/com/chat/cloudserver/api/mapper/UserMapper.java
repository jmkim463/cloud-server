package com.chat.cloudserver.api.mapper;

import com.chat.cloudserver.api.dto.UserDTO;
import com.chat.cloudserver.api.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    public UserDTO entityToDTO(User user);

    public User DTOToEntity(UserDTO dto);

}
