package com.chat.cloudserver.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Entity;
import lombok.*;
import org.springframework.data.annotation.Id;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ParticipantDTO {

    private String no;
    private String userNo;
    private String chatRoomNo;
    private String joinAt;

}
