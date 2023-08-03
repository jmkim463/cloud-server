package com.chat.cloudserver.handler;

import jakarta.websocket.Session;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class WebSocketHandler extends TextWebSocketHandler {

    // key : 회원 no
    private final Map<String, WebSocketSession> sessionMap = new HashMap<>();


    @Override
    protected void handleBinaryMessage(WebSocketSession session, BinaryMessage message) {
        super.handleBinaryMessage(session, message);
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        Map<String, Object> model = session.getAttributes();

        String userNo = (String) model.get("userNo");

        log.info("enter user {} {}", userNo, new Date());


        sessionMap.put(userNo, session);

        super.afterConnectionEstablished(session);
    }
}
