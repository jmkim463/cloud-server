package com.chat.cloudserver.api.dto;

import lombok.Builder;
import lombok.Data;

import java.security.Timestamp;

@Data
@Builder
public class MessageDTO {

    private String id;

    private String senderID;

    private String text;

    private String imageURL;

    private String createDate;

}
