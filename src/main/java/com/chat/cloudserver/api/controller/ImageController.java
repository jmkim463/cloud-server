package com.chat.cloudserver.api.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.data.repository.query.Param;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
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
    public ResponseEntity<?> displayImage(@RequestParam String filename, @RequestParam(required = false) String type) {
        try {
            String file = "/static/images/" + type + "/" + filename;

            Resource resource = new ClassPathResource(file);

            if(!resource.exists()) {
                return new ResponseEntity<Resource>(HttpStatus.NOT_FOUND);
            }

            HttpHeaders header = new HttpHeaders();

            Path path = Paths.get(resource.getFile().getPath());
            header.setContentType(MediaType.valueOf(Files.probeContentType(path)));

            return new ResponseEntity<Resource>(resource, header, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(401).body("Image display failed");
        }
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadImage(@RequestPart MultipartFile image,
                                        @RequestPart String filename,
                                        @RequestPart(required = false) String type) {
        try {
            BufferedImage bufferedImage = ImageIO.read(image.getInputStream());

            String path = "D:/cloud-server/src/main/resources/static/images/" + type + "/" + filename;

            log.info("image upload path = {}, name = {}", path, filename);

            ImageIO.write(bufferedImage, ".jpg", new File(path));

            return ResponseEntity.ok("Image upload success");
        } catch (IOException e) {
            e.printStackTrace();

            return ResponseEntity.status(401).body("Image upload failed");
        }
    }
}
