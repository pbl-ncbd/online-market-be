package com.example.onlinemarketbe.services;


import com.example.onlinemarketbe.model.Category;
import com.example.onlinemarketbe.model.Product;
import com.example.onlinemarketbe.payload.request.CreateProductRequest;
import com.example.onlinemarketbe.payload.request.ListTypeRequest;
import com.example.onlinemarketbe.payload.request.UpdateProductRequest;
import com.example.onlinemarketbe.payload.response.ProductTypeResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductService {
    public ResponseEntity<?> searchProductByCategory(int id);
    ResponseEntity<?> getProductBySale(String username);
    ResponseEntity<?> searchProduct(String keyword);
    ProductTypeResponse getProductById(int id);
    ResponseEntity<?> updateProduct(String username, UpdateProductRequest updateProductRequest,MultipartFile[] listImg, ListTypeRequest[] listTypeRequests);
    ResponseEntity<?> deleteProduct(String username,int id);
    ResponseEntity<?> createProduct(String username, CreateProductRequest createProductRequest, MultipartFile[] listImg, ListTypeRequest[] listTypeRequests);

    ResponseEntity<?> getAllProduct();
    List<Category> getAllCategory();
    
}
