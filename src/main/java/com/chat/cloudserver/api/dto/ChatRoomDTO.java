package com.chat.cloudserver.api.dto;

import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class ChatRoomDTO {

    private String no;
    private String createAt;

}
