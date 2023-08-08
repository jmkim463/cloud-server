package com.chat.cloudserver.jpa.repository;

import com.chat.cloudserver.jpa.entity.ChatRoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomRepository extends JpaRepository<ChatRoomEntity, String> {

}
