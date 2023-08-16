package com.chat.cloudserver.api.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity(name = "message")
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

}
