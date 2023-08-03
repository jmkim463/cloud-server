package com.chat.cloudserver.api.service;

import com.chat.cloudserver.api.dto.UserDTO;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public Integer checkLogin(String id, String password) {
        return 1;
    }

    public UserDTO getUserDTO(int no) {
        UserDTO userDTO = UserDTO.builder()
                .no(1)
                .id("test")
                .password("asdf")
                .name("홍길동")
                .email("test@gmail.com")
                .imageURL("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQikT2F-SoptowIYNW3dsQDHecNmUZCFsv9Og&usqp=CAU")
                .build();

        return userDTO;
    }

    public boolean isHaveSameID(String id) {
        return true;
    }

    public void createUserAccount(UserDTO userDTO) {

    }
}
