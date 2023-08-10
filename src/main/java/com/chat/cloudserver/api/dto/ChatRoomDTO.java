package com.chat.cloudserver.api.dto;

import jakarta.persistence.Entity;
import lombok.*;

@Getter
@ToString
@Builder
public class ChatRoomDTO {

    private String no;
    private String createAt;

}
