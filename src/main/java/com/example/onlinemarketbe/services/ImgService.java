package com.example.onlinemarketbe.services;

import com.example.onlinemarketbe.model.UrlImg;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImgService {
    String uploadImg(MultipartFile file);
   // void deleteImg(List<UrlImg> list);
   ResponseEntity<ByteArrayResource> downloadImg(String publicId);
    boolean deleteImg(String id);
}
