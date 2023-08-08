package com.chat.cloudserver.api.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@Entity(name = "message")
public class MessageDTO {

    @Id
    private String no;

    private String chatRoomNo;

    private String senderNo;

    private String content;

    private String sendAt;

    public MessageDTO(String no, String chatRoomNo, String senderNo, String content, String sendAt) {
        this.no = no;
        this.chatRoomNo = chatRoomNo;
        this.senderNo = senderNo;
        this.content = content;
        this.sendAt = sendAt;
    }

}
