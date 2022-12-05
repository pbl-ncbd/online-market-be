package com.example.onlinemarketbe.services;

import com.example.onlinemarketbe.model.Product;
import com.example.onlinemarketbe.payload.request.CreateProductRequest;
import com.example.onlinemarketbe.payload.request.UpdateProductRequest;
import org.springframework.http.ResponseEntity;

public interface ProductService {
    ResponseEntity<?> getProductBySale(String username);
    Product getProductById(int id);
    ResponseEntity<?> updateProduct(String username, UpdateProductRequest updateProductRequest);
    ResponseEntity<?> deleteProduct(String username,int id);
    ResponseEntity<?> createProduct(String username, CreateProductRequest createProductRequest);
}
