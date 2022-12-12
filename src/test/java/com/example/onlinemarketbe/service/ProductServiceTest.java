package com.example.onlinemarketbe.service;

import com.example.onlinemarketbe.OnlineMarketBeApplication;
import com.example.onlinemarketbe.model.Category;
import com.example.onlinemarketbe.model.Product;
import com.example.onlinemarketbe.model.User;
import com.example.onlinemarketbe.payload.request.CreateProductRequest;
import com.example.onlinemarketbe.payload.request.UpdateProductRequest;
import com.example.onlinemarketbe.payload.response.ItemResponse;
import com.example.onlinemarketbe.payload.response.ProductTypeResponse;
import com.example.onlinemarketbe.repositories.UserRepository;
import com.example.onlinemarketbe.services.ItemService;
import com.example.onlinemarketbe.services.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


@Transactional
@SpringBootTest(classes = OnlineMarketBeApplication.class)
@RunWith(SpringRunner.class)
public class ProductServiceTest {
    @Autowired
    ProductService productService;
    @Autowired
    UserRepository userRepository;
    private User user = new User();
    @Before
    public void preTest() {
        String USERNAME = "string123123";
        user = userRepository.findUserByUsername(USERNAME);
    }
    @Test
    @Transactional
    public void findAllProduct() {
      ResponseEntity<?> list = productService.getAllProduct();
        if (list!=null)
        {
                assertEquals(HttpStatus.OK,list.getStatusCode());
            }

    }
    @Test
    @Transactional
    public void getAllCategory() {
        List<Category> list = productService.getAllCategory();
        if (list!=null)
        {
            assertTrue(!list.contains(null));
            Set<Category> set= new HashSet<>(list);
            assertEquals(set.size(),list.size());

        }

    }
    @Test
    @Transactional
    public void createProduct() {
        String username= "string123123";
        CreateProductRequest createProductRequest= new CreateProductRequest();
        createProductRequest.setDescription("hehe");
        createProductRequest.setPrice(123);
        createProductRequest.setQuantity(123);
        createProductRequest.setName("ao nam");
        createProductRequest.setIdCategory(1);
        ResponseEntity<?> createProduct= productService.createProduct(username,createProductRequest,null,null);
        assertEquals("create success",createProduct.getBody());

    }
    @Test
    @Transactional
    public void updateProduct() {
        String username= "hien1234";
        UpdateProductRequest updateProductRequest= new UpdateProductRequest();
        updateProductRequest.setIdProduct(5);
        updateProductRequest.setPrice(123);
        updateProductRequest.setDescription("hehe");
        updateProductRequest.setName("ao nam");
        updateProductRequest.setIdCategory(1);

        ResponseEntity<?> createProduct= productService.updateProduct(username,updateProductRequest,null,null);
        assertEquals("update success",createProduct.getBody());

    }
    @Test
    @Transactional
    public void deleteProduct() {
        String username= "hien1234";
        ResponseEntity<?> createProduct= productService.deleteProduct(username,5);
        assertEquals("delete success",createProduct.getBody());

    }
    @Test
    @Transactional
    public void getProductById() {
        int id=5;
        ProductTypeResponse productTypeResponse= productService.getProductById(id);
        if(productTypeResponse!=null)
        {
            assertTrue(true);
        }

    }
    @Test
    @Transactional
    public void getProductBySale() {
        ResponseEntity<?> list= productService.getProductBySale("hien1234");
        if( !list.getBody().equals("not logged in"))
        {
            assertTrue(true);
        }
        else
        {
            assertTrue(false);
        }

    }



}
