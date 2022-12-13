package com.example.onlinemarketbe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SellerRegisterDto implements Serializable {
    private MultipartFile[] idCards;
    private String name;
    private String gender;
    private String address;
    private int userId;
    private boolean confirm = false;
}
