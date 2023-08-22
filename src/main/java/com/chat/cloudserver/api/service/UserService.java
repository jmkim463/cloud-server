package com.chat.cloudserver.api.service;

import com.chat.cloudserver.api.dto.UserDTO;
import com.chat.cloudserver.api.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper mapper;

    public Long retrieveNoByIdAndPassword(String id, String password) {
        Long no = mapper.findUserByIDAndPassword(id, password);

        return no;
    }

    public UserDTO findUserByNo(Long no) {
        UserDTO user = mapper.findUserByNo(no);

        return user;
    }

    public boolean isHaveSameID(String id) {
        int count = mapper.countSameID(id);

        return count == 0;
    }

    public Long createUserAccount(UserDTO userDTO) {
        Long no = mapper.saveUser(userDTO);

        return no;
    }

}
