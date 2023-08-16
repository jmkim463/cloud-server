package com.chat.cloudserver.api.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity(name = "user")
public class User {

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

}
