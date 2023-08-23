package com.chat.cloudserver.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MessageDTO {

    private Long no;
    private Long chatroomNo;
    private Long senderNo;
    private UserDTO senderDTO;
    private String content;
    private String sendAt;

    public static String getNowDateTime() {
        return new SimpleDateFormat("yyyy:MM:dd HH:mm:ss").format(new Date());
    }

}
