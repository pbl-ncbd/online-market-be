package com.example.onlinemarketbe.controllers;

import com.example.onlinemarketbe.common.constrants.FilePathConstrants;
import com.example.onlinemarketbe.common.utils.FileUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/images")
public class FileController {
    @GetMapping("/{filename}")
    public ResponseEntity<byte[]> getImage(@PathVariable("filename") String filename){
        byte[] image = FileUtils.getImage(filename);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(image);
    }
}
