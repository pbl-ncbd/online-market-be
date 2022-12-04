package com.example.onlinemarketbe.payload.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UpdateProductRequest {
    private int idCategory;
    private int idProduct;
    private String name;
    private double price;
    private String description;
    private int quantity;
    private List<MultipartFile> fileImg;
    private List<ListTypeRequest> list;
}
