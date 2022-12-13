package com.example.onlinemarketbe.common.utils;

import com.example.onlinemarketbe.common.constrants.FilePathConstrants;
import com.example.onlinemarketbe.common.exception.InvalidFileException;
import com.example.onlinemarketbe.common.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
@Slf4j

public class FileUtils {
    public static boolean checkImageType(MultipartFile file){
        final String[] validTypes = new String[]{"jpg", "png", "jpeg"};
        final String fileExtension = FilenameUtils.getExtension(file.getOriginalFilename());
        log.info(fileExtension);
        return Arrays.asList(validTypes).contains(fileExtension);
    }

    public static byte[] getImage(final String filename) throws NotFoundException {
        byte[] image = new byte[0];
        try {
            image = org.apache.commons.io.FileUtils.readFileToByteArray(new File(FilePathConstrants.USER_IDENTITY_IMAGE_PATH+"/"+filename));
        } catch (IOException e) {
            e.printStackTrace();
            throw new NotFoundException("This File could not be found");
        }
        return image;
    }

    public static String uploadImage(final MultipartFile file) throws InvalidFileException {
        if(!FileUtils.checkImageType(file)) throw new InvalidFileException("Only accept image file");
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Path path = Paths.get(new File(FilePathConstrants.USER_IDENTITY_IMAGE_PATH).getAbsolutePath() + "/" + fileName);
        log.info(path.toString());
        try {
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
            throw new InvalidFileException("Can't save this file, please check it");
        }
        return ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("images/")
                .path(fileName)
                .toUriString();
    }

    public static void deleteFile(String filename){
        try {
            Files.delete(Path.of(new File(FilePathConstrants.USER_IDENTITY_IMAGE_PATH + "/" + filename).getAbsolutePath()));
        } catch (IOException e) {
            e.printStackTrace();
            throw new InvalidFileException("Can not delete file");
        }
    }

}
