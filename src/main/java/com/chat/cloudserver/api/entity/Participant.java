package com.chat.cloudserver.api.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity(name = "participant")
public class Participant {

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

}
