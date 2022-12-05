package com.example.onlinemarketbe.services;

import com.example.onlinemarketbe.model.UrlImg;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImgService {
    String uploadImg(String path, MultipartFile file);
    void deleteImg(List<UrlImg> list);
}
