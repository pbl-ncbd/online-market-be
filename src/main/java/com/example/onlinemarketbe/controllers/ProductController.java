package com.example.onlinemarketbe.controllers;

import com.example.onlinemarketbe.payload.request.CreateProductRequest;
import com.example.onlinemarketbe.payload.request.UpdateProductRequest;
import com.example.onlinemarketbe.services.ImgService;
import com.example.onlinemarketbe.services.ProductService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.AccessType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;

@CrossOrigin()
@RestController
@RequestMapping("/api/auth")

public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    ImgService imgService;
    @PostMapping("/create-product")
    public ResponseEntity<?> createProduct(Principal principal, @RequestBody CreateProductRequest createProductRequest)
    {
        try
        {
            return ResponseEntity.ok(productService.createProduct(principal.getName(), createProductRequest));
        }catch (Exception e)
        {
            return ResponseEntity.ok(e);
        }
    }
    @PostMapping("/update-product")
    public ResponseEntity<?> updateProduct(Principal principal, @RequestBody UpdateProductRequest updateProductRequest)
    {
        try
        {
            return ResponseEntity.ok(productService.updateProduct(principal.getName(), updateProductRequest));
        }catch (Exception e)
        {
            return ResponseEntity.ok(e);
        }
    }
    @PostMapping("/delete-product/{id}")
    public ResponseEntity<?> deleteProduct(Principal principal, @PathVariable int id)
    {
        try
        {
            return ResponseEntity.ok(productService.deleteProduct(principal.getName(), id));
        }catch (Exception e)
        {
            return ResponseEntity.ok(e);
        }
    }


}
