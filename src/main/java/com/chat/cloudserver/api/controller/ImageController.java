package com.chat.cloudserver.api.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.data.repository.query.Param;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

@Slf4j
@RestController
@RequestMapping("/api/image")
public class ImageController {

    @GetMapping("/display")
    public ResponseEntity<?> display(@RequestParam String filename,
                                     @RequestParam(required = false) String type) {
        try {
            String path = "D:/DATA/images/" + type + "/" + filename;
            File file = new File(path);

            if(!file.exists()) {
                return new ResponseEntity<Resource>(HttpStatus.NOT_FOUND);
            }

            HttpHeaders header = new HttpHeaders();
            header.setContentType(MediaType.valueOf(Files.probeContentType(Paths.get(path))));

            return new ResponseEntity<Resource>(new InputStreamResource(new FileInputStream(file)), header, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(401).body("Image display failed");
        }
    }

    @PostMapping("/upload")
    public ResponseEntity<?> upload(@RequestPart MultipartFile image,
                                    @RequestPart String filename,
                                    @RequestPart(required = false) String type) {
        try {
            BufferedImage bufferedImage = ImageIO.read(image.getInputStream());

            String path = "D:/DATA/images/" + type + "/" + filename;
            String extension = filename.substring(filename.lastIndexOf('.') + 1, filename.length());

            log.info("path = {}", path);

            ImageIO.write(bufferedImage, extension, new File(path));

            return ResponseEntity.ok("Image upload success");
        } catch (IOException e) {
            return ResponseEntity.status(401).body("Image upload failed");
        }
    }
}
