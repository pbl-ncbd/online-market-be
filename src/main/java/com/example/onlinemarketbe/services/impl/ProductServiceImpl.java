package com.example.onlinemarketbe.services.impl;

import com.example.onlinemarketbe.model.Product;
import com.example.onlinemarketbe.model.Type;
import com.example.onlinemarketbe.model.UrlImg;
import com.example.onlinemarketbe.model.User;
import com.example.onlinemarketbe.payload.request.CreateProductRequest;
import com.example.onlinemarketbe.payload.request.ListTypeRequest;
import com.example.onlinemarketbe.payload.request.UpdateProductRequest;
import com.example.onlinemarketbe.repositories.ProductRepository;
import com.example.onlinemarketbe.repositories.TypeRepository;
import com.example.onlinemarketbe.repositories.UrlImgRepository;
import com.example.onlinemarketbe.repositories.UserRepository;
import com.example.onlinemarketbe.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CategoryServiceImpl categoryServiceimpl;
    @Autowired
    TypeRepository typeRepository;
    @Autowired
    UrlImgRepository urlImgRepository;
    @Autowired
    ImgServiceImpl imgServiceImpl;
    @Value("${project.img_product}")
    private String img;
    @Override
    public ResponseEntity<?> getProductBySale(String username)
    {
        User user = userRepository.findUserByUsername(username);
        if(user== null)
        {
            return ResponseEntity.ok("not logged in");
        }
        else {
            return ResponseEntity.ok(productRepository.findAllByUserIdAndStatus(user.getId(),true));
        }
    }
    @Override
    public  Product getProductById(int id)
    {
        return productRepository.findProductByIdAndStatus(id, true);
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
            product.setCategory(categoryServiceimpl.getCategoryById(createProductRequest.getIdCategory()));
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
                urlImg.setUrl(imgServiceImpl.uploadImg(img,i));
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
                product.setCategory(categoryServiceimpl.getCategoryById(updateProductRequest.getIdCategory()));
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
                    imgServiceImpl.deleteImg(urlImgs);
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
                        urlImg.setUrl(imgServiceImpl.uploadImg(img, i));
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

}
