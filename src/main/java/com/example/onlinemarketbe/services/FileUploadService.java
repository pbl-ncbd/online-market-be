package com.example.onlinemarketbe.services;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {
    String uploadImage(MultipartFile uploadImage, String name, int userId);
}
