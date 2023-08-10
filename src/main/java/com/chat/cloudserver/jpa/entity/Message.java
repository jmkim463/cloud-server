package com.chat.cloudserver.jpa.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@ToString
@Builder
@NoArgsConstructor
@Entity(name = "message")
@Table(name = "message")
public class Message {

    @Id
    @Column(length = 20)
    @GeneratedValue(strategy = GenerationType.UUID)
    private String no;

    @Column(length = 20)
    private String chatRoomNo;

    @Column(length = 20)
    private String senderNo;

    @Column(length = 2000)
    private String content;

    @Column(columnDefinition = "DATETIME")
    private String sendAt;

    public Message(String no, String chatRoomNo, String senderNo, String content, String sendAt) {
        this.no = no;
        this.chatRoomNo = chatRoomNo;
        this.senderNo = senderNo;
        this.content = content;
        this.sendAt = sendAt;
    }

}
