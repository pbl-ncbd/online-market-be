package com.example.onlinemarketbe.services.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.Singleton;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import com.example.onlinemarketbe.model.UrlImg;
import com.example.onlinemarketbe.repositories.UrlImgRepository;
import com.example.onlinemarketbe.services.ImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.UUID;
@Service
public class ImgServiceImpl implements ImgService {
    @Autowired
    UrlImgRepository urlImgRepository;
    private final Cloudinary cloudinary = Singleton.getCloudinary();
//    @Override
//    public String uploadImg(String path, MultipartFile file)  {
//        String name=file.getOriginalFilename();
//
//        String randomId= UUID.randomUUID().toString();
//        String fileName1 =randomId.concat(name.substring(name.lastIndexOf(".")));
//        String filePath=path+fileName1;
//        File f= new File(path);
//        if(!f.exists())
//        {
//            f.mkdir();
//        }
//        try {
//            Files.copy(file.getInputStream(), Paths.get(filePath));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        return filePath;
//    }
@Override
    public String uploadImg( MultipartFile file)  {
    try {
        Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
        String publicId = uploadResult.get("public_id").toString();
        return publicId;
    } catch (Exception ex) {
        return ""+ex;
    }
    }
    @Override
    public boolean deleteImg(String id)  {
        try {
            Map result = cloudinary.uploader()
                    .destroy(id,
                            ObjectUtils.emptyMap());

            return true;
        } catch (Exception ex) {
           return false;
        }
    }

//@Override
//    public void deleteImg(List<UrlImg> list)  {
//        if(list==null) return;
//        for(UrlImg eventImage: list){
//            try {
//                Path fileToDeletePath = Paths.get(eventImage.getUrl());
//                System.out.println(fileToDeletePath);
//                if(Files.isExecutable(fileToDeletePath))
//                    Files.delete(fileToDeletePath);
//                urlImgRepository.delete(eventImage);
//            }
//            catch(Exception e)
//            {
//
//            }
//        }
//    }
    @Override
    public ResponseEntity<ByteArrayResource> downloadImg(String publicId) {



    // Generates the UR
        String format = "jpg";
        Transformation transformation = new Transformation();
        String cloudUrl = cloudinary.url().secure(true).format(format)
                .transformation(transformation)
                .publicId(publicId)
                .generate();
    try {
        // Get a ByteArrayResource from the URL
        URL url = new URL(cloudUrl);
        InputStream inputStream = url.openStream();
        byte[] out = org.apache.commons.io.IOUtils.toByteArray(inputStream);
        ByteArrayResource resource = new ByteArrayResource(out);

        // Creates the headers
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("content-disposition", "attachment; filename=image.jpg");
        responseHeaders.add("Content-Type", "image/jpeg");
        return ResponseEntity.ok()
                .headers(responseHeaders)
                .contentLength(out.length)
                // .contentType(MediaType.parseMediaType(mimeType))
                .body(resource);
    } catch (Exception ex) {
        return null;
    }
}
}
