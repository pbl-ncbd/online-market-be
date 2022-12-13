package com.example.onlinemarketbe;

import com.cloudinary.Cloudinary;
import com.cloudinary.SingletonManager;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Map;


@SpringBootApplication
public class OnlineMarketBeApplication {

    public static void main(String[] args) {
        Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dpnhk5kup", // insert here you cloud name
                "api_key", "231382571537978", // insert here your api code
                "api_secret", "Hyfcc9NEPniLUJ3xNSeQ5DvLq5g")); // insert here your api secret
        SingletonManager manager = new SingletonManager();
        manager.setCloudinary(cloudinary);
        manager.init();


        SpringApplication.run(OnlineMarketBeApplication.class, args);
    }

}
