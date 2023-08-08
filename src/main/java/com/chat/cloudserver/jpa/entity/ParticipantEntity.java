package com.chat.cloudserver.jpa.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@ToString
@Builder
@NoArgsConstructor
@Entity(name = "participant")
public class ParticipantEntity {

    @Id
    @Column(length = 20)
    @GeneratedValue(strategy = GenerationType.UUID)
    private String no;

    @Column(length = 20)
    private String userNo;

    @Column(length = 20)
    private String chatRoomNo;

    @Column(columnDefinition = "DATETIME")
    private String joinAt;

    public ParticipantEntity(String no, String userNo, String chatRoomNo, String joinAt) {
        this.no = no;
        this.userNo = userNo;
        this.chatRoomNo = chatRoomNo;
        this.joinAt = joinAt;
    }
}
