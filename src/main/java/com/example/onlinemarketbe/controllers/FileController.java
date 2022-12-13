package com.example.onlinemarketbe.controllers;

import com.example.onlinemarketbe.common.constrants.FilePathConstrants;
import com.example.onlinemarketbe.common.utils.FileUtils;
import com.example.onlinemarketbe.services.ImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
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
    @Autowired
    ImgService imgService;

    @GetMapping("/{filename}")
    public  ResponseEntity<ByteArrayResource> downloadAvatar(@PathVariable String filename) {
        return imgService.downloadImg(filename);
    }

}
