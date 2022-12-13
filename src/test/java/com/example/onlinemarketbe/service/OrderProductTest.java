package com.example.onlinemarketbe.service;

import com.example.onlinemarketbe.OnlineMarketBeApplication;
import com.example.onlinemarketbe.payload.request.CreateOrderRequest;
import com.example.onlinemarketbe.payload.request.CreateProductRequest;
import com.example.onlinemarketbe.services.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@Transactional
@SpringBootTest(classes = OnlineMarketBeApplication.class)
@RunWith(SpringRunner.class)
public class OrderProductTest {
    @Autowired
    OrderService orderService;
    @Test
    @Transactional
    public void createProduct() {
        String username= "string123123";
        CreateOrderRequest createOrderRequest= new CreateOrderRequest();
        createOrderRequest.setAddress("hehe");
        createOrderRequest.setProvince("hehe12");
        createOrderRequest.setIdPayment(1);
        List<Integer>  item= new ArrayList<>();
        item.add(2);
        createOrderRequest.setListIdItem(item);
        ResponseEntity<?> createProduct= orderService.createOrder(username,createOrderRequest);
        assertEquals("create success",createProduct.getBody());


    }
    @Test
    @Transactional
    public void getOrder() {
        String username= "string123123";
        ResponseEntity<?> list= orderService.getInfoOrderByOrdered(username);
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