package com.chat.cloudserver.config.handler;

import com.chat.cloudserver.api.dto.MessageDTO;
import com.chat.cloudserver.api.dto.UserDTO;
import com.chat.cloudserver.api.entity.User;
import com.chat.cloudserver.api.repository.UserRepository;
import com.chat.cloudserver.model.MessageType;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.w3c.dom.Text;

import java.io.IOException;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class WebSocketHandler extends TextWebSocketHandler {

    private final Map<Long, WebSocketSession> userSessions;

    private final UserRepository userRepository;
    private final ObjectMapper mapper;

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {

        JSONObject json = (JSONObject) new JSONParser().parse(message.getPayload());

        MessageType type = MessageType.valueOf((String) json.get("type"));
        Long senderNo = (Long) json.get("senderNo");

        switch (type) {
            case ENTER -> enter(senderNo, session);
            case EXIT -> exit(senderNo);
            case MESSAGE -> {

                MessageDTO messageDTO = mapper.readValue(json.get("messageDTO").toString(), MessageDTO.class);

                sendMessage(messageDTO);
            }
        }
    }

    public void enter(Long no, WebSocketSession session) {
        userSessions.put(no, session);
    }

    public void exit(Long no) {
        userSessions.remove(no);
    }

    public void sendMessage(MessageDTO messageDTO) throws IOException {
        Long sender = messageDTO.getSenderDTO().getNo();

        TextMessage message = new TextMessage(mapper.writeValueAsString(messageDTO));

        System.out.println(message);

        for(Long no : userSessions.keySet()) {
            if (sender != no) {
                userSessions.get(no).sendMessage(message);
            }
        }

    }
}
