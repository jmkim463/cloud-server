package com.chat.cloudserver.api.dto;

import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@Builder
public class ParticipantDTO {

    private String no;
    private String userNo;
    private String chatRoomNo;
    private String joinAt;

}
