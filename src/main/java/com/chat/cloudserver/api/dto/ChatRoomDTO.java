package com.chat.cloudserver.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChatRoomDTO {

    private Long no;
    private String name;
    private String createAt;

    private String userIdList;
    private String userNameList;
    private String lastChat;
    private String lastAt;
    private List<Long> participants;
}
