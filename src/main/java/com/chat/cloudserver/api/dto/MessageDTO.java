package com.chat.cloudserver.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MessageDTO {

    private Long no;
    private Long chatroomNo;
    private UserDTO senderDTO;
    private String content;
    private String sendAt;

}
