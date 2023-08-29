package com.chat.cloudserver.api.service;

import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class ImageService {

    public ResponseEntity getImageResponseEntity(String path) throws IOException {
        File file = new File(path);

        if(!file.exists()) {
            return null;
        }

        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.valueOf(Files.probeContentType(Paths.get(path))));

        return new ResponseEntity<Resource>(new InputStreamResource(new FileInputStream(file)), header, HttpStatus.OK);
    }

    public void uploadImage(MultipartFile multipartFile, String path) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(multipartFile.getInputStream());

        String[] split = path.split("/");
        String name = split[split.length - 1];
        String extension = name.substring(name.lastIndexOf('.') + 1, name.length());

        ImageIO.write(bufferedImage, extension, new File(path));
    }

}

