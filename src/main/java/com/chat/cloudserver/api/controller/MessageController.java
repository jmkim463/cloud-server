package com.chat.cloudserver.api.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MessageController {

    /*@SubscribeMapping("/group/chat/{roomID}")
    public void subscribeGroupChat(@DestinationVariable String roomID) {
        log.info("subscribe Group Chat = {}", roomID);
    }

    @MessageMapping("/group/{roomID}")
    @SendTo("/group/chat/{roomID}")
    public String handleGroupChat(@DestinationVariable String roomID, String text) {
        log.info("roomID = {}, message = {}", roomID, text);

        return roomID + " : " + text;
    }

    @MessageMapping("/personal/{userID}")
    @SendTo("/personal/chat/{userID}")
    public String handlePersonalChat(@DestinationVariable String userID, String text) {
        log.info("userID = {}, message = {}", userID, text);

        return userID + " : " + text;
    }

    @MessageMapping("/hello")
    @SendTo("/topic/greeting")
    public String greeting(String text) {
        System.out.println(text);
        return text;
    }*/

}
