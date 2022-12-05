package com.example.onlinemarketbe.services.impl;

import com.example.onlinemarketbe.model.UrlImg;
import com.example.onlinemarketbe.repositories.UrlImgRepository;
import com.example.onlinemarketbe.services.ImgService;
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
public class ImgServiceImpl implements ImgService {
    @Autowired
    UrlImgRepository urlImgRepository;
    @Override
    public String uploadImg(String path, MultipartFile file)  {
        String name=file.getOriginalFilename();

        String randomId= UUID.randomUUID().toString();
        String fileName1 =randomId.concat(name.substring(name.lastIndexOf(".")));
        String filePath=path+fileName1;
        File f= new File(path);
        if(!f.exists())
        {
            f.mkdir();
        }
        try {
            Files.copy(file.getInputStream(), Paths.get(filePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return filePath;
    }
    @Override
    public void deleteImg(List<UrlImg> list)  {
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
