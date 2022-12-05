package com.example.onlinemarketbe.services;

import com.example.onlinemarketbe.payload.request.CreateProductRequest;
import com.example.onlinemarketbe.payload.request.UpdateProductRequest;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

public interface ProductService {

    public ResponseEntity<?> createProduct(String username, CreateProductRequest createProductRequest) throws IOException ;

    public ResponseEntity<?> updateProduct(String username, UpdateProductRequest updateProductRequest) throws IOException ;
    public ResponseEntity<?> deleteProduct(String username,int id);

    public ResponseEntity<?> searchProductByCategory(int id);

}
