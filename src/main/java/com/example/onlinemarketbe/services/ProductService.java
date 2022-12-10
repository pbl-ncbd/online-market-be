package com.example.onlinemarketbe.services;


import com.example.onlinemarketbe.model.Category;
import com.example.onlinemarketbe.payload.request.CreateProductRequest;
import com.example.onlinemarketbe.payload.request.ListImg;
import com.example.onlinemarketbe.payload.request.UpdateProductRequest;
import com.example.onlinemarketbe.payload.response.ProductTypeResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {
    public ResponseEntity<?> searchProductByCategory(int id);
    ResponseEntity<?> getProductBySale(String username);
    ResponseEntity<?> searchProduct(String keyword);
    ProductTypeResponse getProductById(int id);
    ResponseEntity<?> updateProduct(String username, UpdateProductRequest updateProductRequest,MultipartFile[] listImg);
    ResponseEntity<?> deleteProduct(String username,int id);
    ResponseEntity<?> createProduct(String username, CreateProductRequest createProductRequest, MultipartFile[] listImg);

    ResponseEntity<?> getAllProduct();
    List<Category> getAllCategory();
    
}
