package com.chat.cloudserver.api.service;

import com.chat.cloudserver.api.dto.UserDTO;
import com.chat.cloudserver.jpa.entity.User;
import com.chat.cloudserver.jpa.repository.UserRepository;
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


        } else {
            return null;
        }
    }

    public UserDTO getUserDTO(int no) {


        return userDTO;
    }

    public UserDTO test() {
        User entity = repository.findById("USER-000001").get();

        UserDTO userDTO = UserDTO.builder()
                .no(entity.getNo())
                .password(entity.getPassword())
                .id(entity.getId())
                .name(entity.getName())
                .email(entity.getEmail()).build();
        return userDTO ;
    }

    public boolean isHaveSameID(String id) {
        return true;
    }

    public void createUserAccount(UserDTO userDTO) {

    }

}
