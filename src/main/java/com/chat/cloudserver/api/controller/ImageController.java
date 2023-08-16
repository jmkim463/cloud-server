package com.chat.cloudserver.api.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
@RestController
@RequestMapping("/api/image")
public class ImageController {

    @GetMapping("/display/user")
    public ResponseEntity<?> displayUserImage(@RequestParam String filename) throws IOException {
        String file = "/static/images/" + filename;

        Resource resource = new ClassPathResource(file);

        if(!resource.exists()) {
            return new ResponseEntity<Resource>(HttpStatus.NOT_FOUND);
        }

        HttpHeaders header = new HttpHeaders();

        Path path = Paths.get(resource.getFile().getPath());
        header.setContentType(MediaType.valueOf(Files.probeContentType(path)));

        return new ResponseEntity<Resource>(resource, header, HttpStatus.OK);
    }

}
