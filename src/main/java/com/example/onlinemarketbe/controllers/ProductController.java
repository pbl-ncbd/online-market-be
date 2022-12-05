package com.example.onlinemarketbe.controllers;

import com.example.onlinemarketbe.payload.request.CreateProductRequest;
import com.example.onlinemarketbe.payload.request.UpdateProductRequest;

import com.example.onlinemarketbe.services.ProductService;
import com.example.onlinemarketbe.services.impl.ImgServiceImpl;
import com.example.onlinemarketbe.services.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.onlinemarketbe.services.ImgService;
import io.swagger.v3.oas.annotations.Operation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@CrossOrigin()
@RestController
@RequestMapping("/api/auth")

public class ProductController {
     private final ProductService productService;

      private final ImgService imgService;
    

    public ProductController(ProductService productService, ImgService imgService) {
        this.productService = productService;
        this.imgService = imgService;
    }
   
    @GetMapping("/get-product")
    public ResponseEntity<?> getProduct(Principal principal)
    {
        try
        {
            return ResponseEntity.ok(productService.getProductBySale(principal.getName()));
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
            return ResponseEntity.ok(productService.getProductById(id));
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

    @GetMapping("/search-product/{keyword}")
    @Operation(summary = "05/12/2022 by Linh : This is find product by name or category name")
    public ResponseEntity<?> searchProduct(@PathVariable String keyword){
        try{
            return ResponseEntity.ok(productService.searchProduct(keyword));
        }
        catch (Exception e){
            return ResponseEntity.ok(e);
        }
    }

    @GetMapping("/search-product-by-category-id/{id}")
    @Operation(summary = "05/12/2022 by Linh : This is find product by category")
    public ResponseEntity<?> searchProductByCategory(@PathVariable @Valid int id){
        try{
            return ResponseEntity.ok(productService.searchProductByCategory(id));
        }
        catch (Exception e){
            return ResponseEntity.ok(e);
        }
    }


}
