package com.chat.cloudserver.api.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Data
@Builder
public class MessageDTO {

    private String no;
    private String chatRoomNo;
    private String senderNo;
    private String content;
    private String sendAt;

}
