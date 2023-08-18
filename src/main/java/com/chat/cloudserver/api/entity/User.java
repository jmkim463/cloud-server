package com.chat.cloudserver.api.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@Entity(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long no;

    private String name;

    private String id;

    private String password;

    private String email;

    public User(Long no, String name, String id, String password, String email) {
        this.no = no;
        this.name = name;
        this.id = id;
        this.password = password;
        this.email = email;
    }

}
