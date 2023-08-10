package com.chat.cloudserver.api.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@ToString
@Builder
public class UserDTO {

    private String no;
    private String name;
    private String id;
    private String password;
    private String email;
    private String imageURL;

}
