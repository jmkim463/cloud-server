package com.chat.cloudserver.api.controller;

import com.chat.cloudserver.api.dto.ChatRoomDTO;
import com.chat.cloudserver.api.dto.MessageDTO;
import com.chat.cloudserver.api.dto.UserDTO;
import com.chat.cloudserver.api.service.ChatRoomService;
import com.chat.cloudserver.api.service.ImageService;
import com.chat.cloudserver.api.service.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class ChatController {

    private final ChatRoomService chatRoomService;
    private final MessageService messageService;
    private final ImageService imageService;

    @Value("${info.application.image.directory}")
    private String imageDirectory;

    @GetMapping("/chatroom/{userNo}")
    public ResponseEntity<?> getUserChatRoomList(@PathVariable Long userNo) {
        List<ChatRoomDTO> chatRoomDTOList = chatRoomService.selectUserChatRoom(userNo);

        if(chatRoomDTOList == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("");
        }

        return ResponseEntity.ok(chatRoomDTOList);
    }

    @GetMapping("/chatroom")
    public ResponseEntity<?> getPrivateChatRoom(@RequestParam("userNo1") Long userNo1, @RequestParam("userNo2") Long userNo2) {
        ChatRoomDTO chatRoomDTO = chatRoomService.getPrivateChatroom(userNo1, userNo2);

        return ResponseEntity.ok(chatRoomDTO);
    }

    @PostMapping("/chatroom")
    public ResponseEntity<?> saveGroupChatRoom(@RequestBody ChatRoomDTO chatRoomDTO) {
        chatRoomService.saveGroupChatRoom(chatRoomDTO);

        return ResponseEntity.ok("save chatroom complete");
    }

    @GetMapping("/massage")
    public ResponseEntity<?> getMessageOfChatRoomList(@RequestParam("chatroomNo") Long chatroomNo) {
        List<MessageDTO> messageDTOList = messageService.getChatroomOfMessageList(chatroomNo);

        return ResponseEntity.ok(messageDTOList);
    }

    @GetMapping("/image")
    public ResponseEntity displayImage(@RequestParam String content) {
        String name = content.replace("image://", "");
        String path = imageDirectory + "/chat/" + name + ".jpg";

        try {
            return imageService.getImageResponseEntity(path);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("display image failed");
        }
    }

    @PostMapping("/image")
    public ResponseEntity<?> uploadImage(@RequestPart MultipartFile image) {
        String name = UUID.randomUUID().toString();
        String path = imageDirectory + "/chat/" + name + ".jpg";

        try {
            imageService.uploadImage(image, path);

            JSONObject json = new JSONObject();
            json.put("url", "image://" + name);

            return ResponseEntity.ok(json);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Image Upload Failed");
        }
    }

}
