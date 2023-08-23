package com.chat.cloudserver.config.handler;

import com.chat.cloudserver.api.dto.MessageDTO;
import com.chat.cloudserver.api.dto.UserDTO;
import com.chat.cloudserver.api.mapper.MessageMapper;
import com.chat.cloudserver.api.service.MessageService;
import com.chat.cloudserver.api.service.ParticipantService;
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
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
@RequiredArgsConstructor
public class WebSocketHandler extends TextWebSocketHandler {

    private final Map<Long, WebSocketSession> userSessions = new ConcurrentHashMap<>();

    private final MessageService messageService;
    private final ParticipantService participantService;

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
        messageDTO = messageService.save(messageDTO);

        Long senderNo = messageDTO.getSenderNo();
        List<Long> participantList = participantService.getOtherParticipantsInChatRoom(senderNo, messageDTO.getChatroomNo());

        TextMessage message = new TextMessage(mapper.writeValueAsString(messageDTO));

        for(Long no : participantList) {
            WebSocketSession session = userSessions.get(no);

            if(session != null) {
                session.sendMessage(message);
            }
        }

    }

}
