package com.example.onlinemarketbe.services;


import com.example.onlinemarketbe.model.Item;
import com.example.onlinemarketbe.payload.request.CreateItemRequest;
import com.example.onlinemarketbe.payload.request.UpdateItemRequest;
import com.example.onlinemarketbe.payload.response.ItemResponse;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ItemService {
    ItemResponse findItemById(int id, String username);
    List<ItemResponse> findAllItemByUser(String username);
    List<ItemResponse> findAllItemByOrderId(int id, String username);
    boolean deleteItemById(int id, String username);
    ItemResponse updateItem(UpdateItemRequest updateItemRequest, String username);
    ItemResponse addItemToCart(CreateItemRequest createItemRequest, String username);


}
