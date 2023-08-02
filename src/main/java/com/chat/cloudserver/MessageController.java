package com.chat.cloudserver;

import com.chat.cloudserver.dto.MessageDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MessageController {

    // 1:N 쳇팅
    @MessageMapping("/chat/topic/{roomID}")
    @SendTo("/topic/chat/{roomID}")
    public void handleGroupChat(@DestinationVariable(value = "roomID") final String roomID, String text) {
        log.info("roomID = {}, message = {}", roomID, text);//message.getText());
    }


    // 1:1 챗팅
    @MessageMapping("/chat/{userID}")
    @SendTo("/queue/chat/{userID}")
    public void handlePrivateChat(@DestinationVariable(value = "userID") final String userID, MessageDTO message) {
        log.info("userID = {}, message = {}", userID, message.getText());

    }
}
