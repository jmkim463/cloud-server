package com.chat.cloudserver.api.service;

import com.chat.cloudserver.api.dto.FriendDTO;
import com.chat.cloudserver.api.dto.UserDTO;
import com.chat.cloudserver.api.mapper.FriendMapper;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FriendService {

    private final FriendMapper mapper;

    public List<UserDTO> getFriendList(Long userNo) {
        List<UserDTO> list = mapper.selectFriendList(userNo);
        return list;
    }

    public List<UserDTO> getPendingFriendList(Long userNo) {
        List<UserDTO> list = mapper.selectPendingFriendList(userNo);
        return list;
    }

    public List<UserDTO> getNotFriendUserList(String keyword, Long userNo) {
        List<UserDTO> list = mapper.selectNotFriendUserList(keyword, userNo);
        return list;
    }

    public Long save(FriendDTO friendDTO) {
        return mapper.save(friendDTO);
    }
}
