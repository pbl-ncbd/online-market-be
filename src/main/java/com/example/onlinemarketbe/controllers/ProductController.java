package com.example.onlinemarketbe.controllers;

import com.example.onlinemarketbe.payload.request.CreateProductRequest;
import com.example.onlinemarketbe.payload.request.UpdateProductRequest;
import com.example.onlinemarketbe.services.impl.ImgServiceImpl;
import com.example.onlinemarketbe.services.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@CrossOrigin()
@RestController
@RequestMapping("/api/auth")

public class ProductController {
    @Autowired
    ProductServiceImpl productServiceImpl;
    @Autowired
    ImgServiceImpl imgServiceImpl;
    @GetMapping("/get-product")
    public ResponseEntity<?> getProduct(Principal principal)
    {
        try
        {
            return ResponseEntity.ok(productServiceImpl.getProductBySale(principal.getName()));
        }catch (Exception e)
        {
            return ResponseEntity.ok(e);
        }
    }
    @GetMapping("/get-product/{id}")
    public ResponseEntity<?> getProductById(@PathVariable int id)
    {
        try
        {
            return ResponseEntity.ok(productServiceImpl.getProductById(id));
        }catch (Exception e)
        {
            return ResponseEntity.ok(e);
        }
    }
    @PostMapping("/create-product")
    public ResponseEntity<?> createProduct(Principal principal, @RequestBody CreateProductRequest createProductRequest)
    {
        try
        {
            return ResponseEntity.ok(productServiceImpl.createProduct(principal.getName(), createProductRequest));
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
            return ResponseEntity.ok(productServiceImpl.updateProduct(principal.getName(), updateProductRequest));
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
            return ResponseEntity.ok(productServiceImpl.deleteProduct(principal.getName(), id));
        }catch (Exception e)
        {
            return ResponseEntity.ok(e);
        }
    }


}
