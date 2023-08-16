package com.chat.cloudserver.api.service;

import com.chat.cloudserver.api.dto.UserDTO;
import com.chat.cloudserver.api.entity.User;
import com.chat.cloudserver.api.mapper.UserMapper;
import com.chat.cloudserver.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public String retrieveUserNoByIdAndPassword(String id, String password) {
        Optional<String> optional = repository.findUserNoByIdAndPassword(id, password);

        if(optional.isPresent()) {
            String userNo = optional.get();
            return userNo;
        } else {
            return null;
        }
    }

    public UserDTO findUserByNo(String userNo) {
        Optional<User> optional = repository.findById(userNo);

        if(optional.isPresent()) {
            User entity = optional.get();

            return UserMapper.INSTANCE.entityToDTO(entity);
        } else {
            return null;
        }
    }

    public boolean isHaveSameID(String id) {
        return true;
    }

    public void createUserAccount(UserDTO userDTO) {

    }

}
