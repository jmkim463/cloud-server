package com.chat.cloudserver.api.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTO {

    private int no;

    private String id;

    private String password;

    private String name;

    private String email;

    private String imageURL;

}