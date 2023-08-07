package com.chat.cloudserver.config.handler;

import com.chat.cloudserver.model.MessageType;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class WebSocketHandler extends TextWebSocketHandler {

    Map<String, WebSocketSession> userSessions = new HashMap<>();

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        System.out.println(message.getPayload());

        JSONObject json = (JSONObject) new JSONParser().parse(message.getPayload());

        MessageType type = MessageType.valueOf((String) json.get("type"));

        switch (type) {
            case ENTER -> enter(session, json);
            case MESSAGE -> sendMessage(session, json);
            case EXIT -> exit(session, json);
        }
    }

    private void enter(WebSocketSession session, JSONObject json) {
        String userNo = (String) json.get("userNo");

        userSessions.put(userNo, session);
    }

    private void sendMessage(WebSocketSession session, JSONObject json) throws IOException {
        String userNo = (String) json.get("userNo");
        String message = (String) json.get("message");

        log.info("USER = {} SEND MESSAGE = {}", userNo, message);
        for(String key : userSessions.keySet()) {
            if (!userNo.equals(key)) {
                userSessions.get(key).sendMessage(new TextMessage(message));
            }
        }
    }

    private void exit(WebSocketSession session, JSONObject json) {
        String userNo = (String) json.get("userNo");

        log.info("USER = {} EXIT", userNo);

        userSessions.remove(userNo);
    }
}
