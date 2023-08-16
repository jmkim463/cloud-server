package com.chat.cloudserver.api.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity(name = "chatroom")
public class ChatRoom {

    @Id
    @Column(length = 20)
    @GeneratedValue(strategy = GenerationType.UUID)
    private String no;

    @Column(columnDefinition = "DATETIME")
    private String createAt;

}
