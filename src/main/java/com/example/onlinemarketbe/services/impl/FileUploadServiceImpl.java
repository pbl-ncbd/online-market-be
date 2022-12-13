package com.example.onlinemarketbe.services.impl;

import com.example.onlinemarketbe.common.constrants.FilePathConstrants;
import com.example.onlinemarketbe.services.FileUploadService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadServiceImpl implements FileUploadService {
    @Override
    public String uploadImage(MultipartFile uploadImage, String name, int userId) {
//        String fullPath = FilePathConstrants.USER_IDENTITY_IMAGE_PATH + userId+"__"+name+;
        return "";
    }

}
