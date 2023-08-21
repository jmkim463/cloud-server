package com.chat.cloudserver.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Entity;
import lombok.*;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChatRoomDTO {

    private String no;
    private String createAt;

}
