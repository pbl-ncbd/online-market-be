package com.example.onlinemarketbe.services;

import com.example.onlinemarketbe.model.UrlImg;
import com.example.onlinemarketbe.repositories.UrlImgRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
@Service
public class ImgService {
    @Autowired
    UrlImgRepository urlImgRepository;
    public String uploadImg(String path, MultipartFile file) throws IOException {
        String name=file.getOriginalFilename();

        String randomId= UUID.randomUUID().toString();
        String fileName1 =randomId.concat(name.substring(name.lastIndexOf(".")));
        String filePath=path+fileName1;
        File f= new File(path);
        if(!f.exists())
        {
            f.mkdir();
        }
        Files.copy(file.getInputStream(), Paths.get(filePath));
        return filePath;
    }
    public void deleteImg(List<UrlImg> list) throws IOException {
        if(list==null) return;
        for(UrlImg eventImage: list){
            try {
                Path fileToDeletePath = Paths.get(eventImage.getUrl());
                System.out.println(fileToDeletePath);
                if(Files.isExecutable(fileToDeletePath))
                    Files.delete(fileToDeletePath);
                urlImgRepository.delete(eventImage);
            }
            catch(Exception e)
            {

            }
        }
    }
}
