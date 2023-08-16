package com.chat.cloudserver.api.dto;

import jakarta.persistence.Entity;
import lombok.*;

@Data
@Builder
public class ChatRoomDTO {

    private String no;
    private String createAt;

}
