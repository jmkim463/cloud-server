package com.chat.cloudserver.api.service;

import com.chat.cloudserver.api.dto.UserDTO;
import com.chat.cloudserver.api.entity.User;
import com.chat.cloudserver.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public Long retrieveNoByIdAndPassword(String id, String password) {
        Optional<Long> optional = repository.findNoByIdAndPassword(id, password);

        if(optional.isPresent()) {
            Long no = optional.get();

            return no;
        } else {
            return null;
        }
    }

    public UserDTO findUserByNo(Long no) {
        Optional<User> optional = repository.findById(no);

        if(optional.isPresent()) {
            User entity = optional.get();

            UserDTO user = UserDTO.builder()
                    .no(entity.getNo())
                    .id(entity.getId())
                    .password(entity.getPassword())
                    .name(entity.getName())
                    .email(entity.getEmail()).build();

            return user;
        } else {
            return null;
        }
    }

    public boolean isHaveSameID(String id) {
        int count = repository.countBySameID(id).get();

        return count == 0;
    }

    public Long createUserAccount(UserDTO userDTO) {
        User entity = User.builder()
                .id(userDTO.getId())
                .password(userDTO.getPassword())
                .name(userDTO.getName())
                .email(userDTO.getEmail()).build();

        entity = repository.save(entity);

        return entity.getNo();
    }

}
