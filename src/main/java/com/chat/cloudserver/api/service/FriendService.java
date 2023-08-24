package com.chat.cloudserver.api.service;

import com.chat.cloudserver.api.dto.UserDTO;
import com.chat.cloudserver.api.mapper.FriendMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FriendService {

    private final FriendMapper mapper;

    public List<UserDTO> getUserFriendList(int status, Long userNo) {
        List<UserDTO> list = mapper.selectFriendList(status, userNo);
        return list;
    }


}
