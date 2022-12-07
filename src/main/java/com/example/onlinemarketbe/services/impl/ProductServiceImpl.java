package com.example.onlinemarketbe.services.impl;
import com.example.onlinemarketbe.model.*;
import com.example.onlinemarketbe.payload.request.CreateProductRequest;
import com.example.onlinemarketbe.payload.request.ListTypeRequest;
import com.example.onlinemarketbe.payload.request.UpdateProductRequest;
import com.example.onlinemarketbe.payload.response.ProductResponse;
import com.example.onlinemarketbe.payload.response.ProductTypeResponse;
import com.example.onlinemarketbe.repositories.ProductRepository;
import com.example.onlinemarketbe.repositories.TypeRepository;
import com.example.onlinemarketbe.repositories.UrlImgRepository;
import com.example.onlinemarketbe.repositories.UserRepository;
import com.example.onlinemarketbe.services.CategoryService;
import com.example.onlinemarketbe.services.ImgService;
import com.example.onlinemarketbe.services.ProductService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final UserRepository userRepository;

    private final CategoryService categoryService;
    private final TypeRepository typeRepository;

    private final UrlImgRepository urlImgRepository;

    private final ImgService imgService;
    @Value("${project.img_product}")
    private String img;

    public ProductServiceImpl(ProductRepository productRepository, UserRepository userRepository, CategoryService categoryService, TypeRepository typeRepository, UrlImgRepository urlImgRepository, ImgService imgService) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.categoryService = categoryService;
        this.typeRepository = typeRepository;
        this.urlImgRepository = urlImgRepository;
        this.imgService = imgService;
    }

    @Override
    public ResponseEntity<?> getProductBySale(String username)
    {
        User user = userRepository.findUserByUsername(username);
        if(user== null)
        {
            return ResponseEntity.ok("not logged in");
        }
        else {
              List<ProductTypeResponse> list = new ArrayList<>();
              List<Product> list1= productRepository.findAllByUserIdAndStatus(user.getId(),true);
              for(Product i: list1)
              {
                  ProductTypeResponse productTypeResponse = new ProductTypeResponse();
                  productTypeResponse.setProduct(i);
                  productTypeResponse.setTypeList(typeRepository.findAllByProductId(i.getId()));
                  list.add(productTypeResponse);
              }
            return ResponseEntity.ok(list);
        }
    }
    @Override
    public ProductTypeResponse getProductById(int id)
    {
        Product i= productRepository.findProductByIdAndStatus(id, true);
        ProductTypeResponse productTypeResponse = new ProductTypeResponse();
            productTypeResponse.setProduct(i);
            productTypeResponse.setTypeList(typeRepository.findAllByProductId(i.getId()));

        return productTypeResponse;
    }

 
  @Override
    public ResponseEntity<?> createProduct(String username, CreateProductRequest createProductRequest)  {
        User user = userRepository.findUserByUsername(username);
        if(user== null)
        {
            return ResponseEntity.ok("not logged in");
        }
        else
        {
            Product product= new Product();
            product.setCategory(categoryService.getCategoryById(createProductRequest.getIdCategory()));
            product.setName(createProductRequest.getName());
            product.setPrice(createProductRequest.getPrice());
            product.setQuantity(createProductRequest.getQuantity());
            product.setUser(user);
            product.setDescription(createProductRequest.getDescription());
            product.setStatus(true);
            Product product1=productRepository.save(product);
            List<MultipartFile> files= createProductRequest.getFileImg();
            if(files!=null)
            {  for(MultipartFile i: files) {
                UrlImg urlImg = new UrlImg();
                urlImg.setProduct(product1);
                urlImg.setUrl(imgService.uploadImg(img,i));
                urlImgRepository.save(urlImg);
            }
            }
            List<ListTypeRequest> listTypeRequests= createProductRequest.getList();
            if(listTypeRequests!=null)
            {
                for(ListTypeRequest i:listTypeRequests)
                {
                    Type type= new Type();
                    type.setProduct(product1);
                    type.setStatus(true);
                    type.setName(i.getName());
                    type.setSize(i.getSize());
                    type.setColor(i.getColor());
                    typeRepository.save(type);

                }
            }
            return ResponseEntity.ok("create success");



        }
    }

    @Override
    public ResponseEntity<?> getAllProduct() {

        List<ProductTypeResponse> list = new ArrayList<>();
        List<Product> list1= productRepository.findAllByStatus(true);
        for(Product i: list1)
        {
            ProductTypeResponse productTypeResponse = new ProductTypeResponse();
            productTypeResponse.setProduct(i);
            productTypeResponse.setTypeList(typeRepository.findAllByProductId(i.getId()));
            list.add(productTypeResponse);
        }
        return ResponseEntity.ok(list);
    }

    @Override
    public ResponseEntity<?> updateProduct(String username, UpdateProductRequest updateProductRequest) {
        User user = userRepository.findUserByUsername(username);
        if(user== null)
        {
            return ResponseEntity.ok("not logged in");
        }
        else
        {

            Product product= productRepository.findProductByIdAndStatus(updateProductRequest.getIdProduct(),true);
            if(product==null) return ResponseEntity.ok("product empty");
            else {
                product.setCategory(categoryService.getCategoryById(updateProductRequest.getIdCategory()));
                product.setName(updateProductRequest.getName());
                product.setPrice(updateProductRequest.getPrice());
                product.setQuantity(updateProductRequest.getQuantity());
                product.setUser(user);
                product.setDescription(updateProductRequest.getDescription());
                productRepository.save(product);
                //delete img old
                List<UrlImg> urlImgs= urlImgRepository.findAllByProductId(product.getId());
                if(urlImgs!=null)
                {

                    imgService.deleteImg(urlImgs);
                }
                List<Type> types= typeRepository.findAllByProductId(product.getId());

                if(types!=null)
                {
                    for(Type type:types)
                    {

                        typeRepository.delete(type);
                    }
                }

                List<MultipartFile> files = updateProductRequest.getFileImg();
                if (files != null) {
                    for (MultipartFile i : files) {
                        UrlImg urlImg = new UrlImg();
                        urlImg.setProduct(product);
                        urlImg.setUrl(imgService.uploadImg(img, i));
                        urlImgRepository.save(urlImg);
                    }
                }
                List<ListTypeRequest> listTypeRequests = updateProductRequest.getList();
                if (listTypeRequests != null) {
                    for (ListTypeRequest i : listTypeRequests) {
                        Type type = new Type();
                        type.setProduct(product);
                        type.setStatus(true);
                        type.setName(i.getName());
                        type.setSize(i.getSize());
                        type.setColor(i.getColor());
                        typeRepository.save(type);

                    }
                }
            }
            return ResponseEntity.ok("update success");



        }
    }

    @Override
    public ResponseEntity<?> deleteProduct(String username,int id)
    {
        User user = userRepository.findUserByUsername(username);
        if(user== null)
        {
            return ResponseEntity.ok("not logged in");
        }
        else {

            Product product = productRepository.findProductByIdAndStatus(id, true);
            if (product == null) return ResponseEntity.ok("product empty");
            else {
                product.setStatus(false);
                productRepository.save(product);
                return ResponseEntity.ok("delete success");
            }

        }
    }
 @Override
    public ResponseEntity<?> searchProduct(String keyword){

     List<ProductTypeResponse> list = new ArrayList<>();
     List<Product> productList = productRepository.searchProduct(keyword.toLowerCase());
     for(Product i: productList)
     {
         ProductTypeResponse productTypeResponse = new ProductTypeResponse();
         productTypeResponse.setProduct(i);
         productTypeResponse.setTypeList(typeRepository.findAllByProductId(i.getId()));
         list.add(productTypeResponse);
     }

        if (list == null){
            return ResponseEntity.ok("Couldn't find any matching products");
        }
        else{
            return ResponseEntity.ok(list);
        }
    }
 @Override
    public ResponseEntity<?> searchProductByCategory(int id){
        Category category = categoryService.getCategoryById(id);
        if (category != null && category.isStatus()){
            List<ProductTypeResponse> list = new ArrayList<>();
            List<Product> productList = productRepository.findProductByCategoryId(id);
            for(Product i: productList)
            {
                ProductTypeResponse productTypeResponse = new ProductTypeResponse();
                productTypeResponse.setProduct(i);
                productTypeResponse.setTypeList(typeRepository.findAllByProductId(i.getId()));
                list.add(productTypeResponse);
            }
            if (list == null){
                return ResponseEntity.ok("Couldn't find any matching products");
            }
            else{
                return ResponseEntity.ok(list);
            }
        }
        else return ResponseEntity.ok("Category not found or deleted ");

    }
    @Override
    public List<Category> getAllCategory()
    {
        return categoryService.getListCategory();
    }

}

