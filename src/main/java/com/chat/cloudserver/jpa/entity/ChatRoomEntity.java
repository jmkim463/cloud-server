package com.chat.cloudserver.jpa.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@ToString
@Builder
@NoArgsConstructor
@Entity(name = "chatroom")
public class ChatRoomEntity {

    @Id
    @Column(length = 20)
    @GeneratedValue(strategy = GenerationType.UUID)
    private String no;

    @Column(columnDefinition = "DATETIME")
    private String createAt;

    public ChatRoomEntity(String no, String createAt) {
        this.no = no;
        this.createAt = createAt;
    }
}
