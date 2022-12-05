package com.example.onlinemarketbe.services;


import com.example.onlinemarketbe.payload.request.CreateProductRequest;
import com.example.onlinemarketbe.payload.request.UpdateProductRequest;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

public interface ProductService {
    public ResponseEntity<?> searchProductByCategory(int id);
    ResponseEntity<?> getProductBySale(String username);
    ResponseEntity<?> searchProduct(String keyword);
    Product getProductById(int id);
    ResponseEntity<?> updateProduct(String username, UpdateProductRequest updateProductRequest);
    ResponseEntity<?> deleteProduct(String username,int id);
    ResponseEntity<?> createProduct(String username, CreateProductRequest createProductRequest);
    
}
