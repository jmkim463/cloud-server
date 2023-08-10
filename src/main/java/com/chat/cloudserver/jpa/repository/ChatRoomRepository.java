package com.chat.cloudserver.jpa.repository;

import com.chat.cloudserver.jpa.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, String> {

}
