package com.chat.cloudserver.api.controller;

import com.chat.cloudserver.api.dto.UserDTO;
import com.chat.cloudserver.api.service.ImageService;
import com.chat.cloudserver.api.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final ImageService imageService;

    @Value("${info.application.image.directory}")
    private String imageDirectory;

    @GetMapping("/account")
    public ResponseEntity<?> login(@RequestParam String id, @RequestParam String password) {
        Long no = userService.retrieveNoByIdAndPassword(id, password);
        UserDTO userDTO = userService.findUserByNo(no);

        return ResponseEntity.ok(userDTO);
    }

    @PostMapping("/account")
    public ResponseEntity<?> createAccount(@RequestBody UserDTO userDTO) {
        Long no = userService.createUserAccount(userDTO);

        return ResponseEntity.ok(no);
    }

    @PutMapping("/account")
    public ResponseEntity<?> updateAccount(@RequestBody UserDTO userDTO) {
        Long no = userService.updateAccount(userDTO);

        return ResponseEntity.ok(no);
    }

    @GetMapping("/check/id")
    public ResponseEntity<Boolean> checkSameId(@RequestParam String id) {
        boolean isHaveSameID = userService.isHaveSameID(id);

        return ResponseEntity.ok(isHaveSameID);
    }

    @GetMapping("/image")
    public ResponseEntity<?> displayImage(@RequestParam String id) {
        int code = id.hashCode();
        String path = imageDirectory + "/user/" + code + ".jpg";

        try {
            return imageService.getImageResponseEntity(path);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("image display failed");
        }
    }

    @PostMapping("/image")
    public ResponseEntity<?> uploadImage(@RequestPart MultipartFile image, @RequestPart String id) {
        String path = imageDirectory + "/user/" + id.hashCode() + ".jpg";

        log.info("test id = {}  ", id);

        try {
            imageService.uploadImage(image, path);

            return ResponseEntity.ok("User Image upload Success");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User Image Upload Failed");
        }
    }

}
