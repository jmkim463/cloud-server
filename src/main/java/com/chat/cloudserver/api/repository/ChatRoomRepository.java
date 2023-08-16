package com.chat.cloudserver.api.repository;

import com.chat.cloudserver.api.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, String> {

}
