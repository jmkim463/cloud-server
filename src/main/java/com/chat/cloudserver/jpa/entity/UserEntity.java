package com.chat.cloudserver.jpa.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@ToString
@Builder
@NoArgsConstructor
@Entity(name = "user")
public class UserEntity {

    @Id
    @Column(length = 20)
    @GeneratedValue(strategy = GenerationType.UUID)
    private String no;

    @Column(length = 50)
    private String name;

    @Column(length = 25)
    private String id;

    @Column(length = 50)
    private String password;

    @Column(length = 50)
    private String email;

    private String imageFilePath;

    public UserEntity(String no, String name, String id, String password, String email, String imageFilePath) {
        this.no = no;
        this.name = name;
        this.id = id;
        this.password = password;
        this.email = email;
        this.imageFilePath = imageFilePath;
    }
}
