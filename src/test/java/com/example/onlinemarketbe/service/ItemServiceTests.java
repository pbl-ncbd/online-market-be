package com.example.onlinemarketbe.service;

import com.example.onlinemarketbe.OnlineMarketBeApplication;
import com.example.onlinemarketbe.model.User;
import com.example.onlinemarketbe.payload.request.CreateItemRequest;
import com.example.onlinemarketbe.payload.request.UpdateItemRequest;
import com.example.onlinemarketbe.payload.response.ItemResponse;
import com.example.onlinemarketbe.repositories.UserRepository;
import com.example.onlinemarketbe.services.ItemService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@Transactional
@SpringBootTest(classes = OnlineMarketBeApplication.class)
@RunWith(SpringRunner.class)
public class ItemServiceTests {
    @Autowired
    ItemService itemService;
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
    public void findItemById() {
        Integer itemId = 2;
        ItemResponse item = itemService.findItemById(itemId, user.getUsername());
        if(item != null){
            assertEquals(itemId, item.getId());
            assertEquals(user.getId(), item.getUserId());
        }
        itemId = 3;
        item = itemService.findItemById(itemId, user.getUsername());
        if(item != null){
            assertEquals(itemId, item.getId());
            assertEquals(user.getId(), item.getUserId());
        }
    }
    @Test
    @Transactional
    public void findAllItemByUser() {
        List<ItemResponse> items = itemService.findAllItemByUser(user.getUsername());
        if (items.size() > 0)
            for (ItemResponse itemResponse : items) {
                assertEquals(itemResponse.getUserId(), user.getId());
            }
    }
    @Test
    @Transactional
    public void findAllItemByOrderId() {
        int orderId = 1;
        List<ItemResponse> items = itemService.findAllItemByOrderId(orderId, user.getUsername());
        if (items.size() > 0)
            for (ItemResponse itemResponse : items) {
                assertEquals(itemResponse.getUserId(), user.getId());
            }

    }
    @Test
    @Transactional
    public void deleteItemById() {
        int itemId = 2;
        ItemResponse item = itemService.findItemById(itemId, user.getUsername());
        if(item != null){
            boolean success = itemService.deleteItemById(itemId, user.getUsername());
            assertTrue(success);
        }
        assertTrue(true);

    }
    @Test
    @Transactional
    public void updateItem() {
        UpdateItemRequest updateItemRequest = new UpdateItemRequest();
        updateItemRequest.setItemId(2);
        updateItemRequest.setQuantity(2);
        updateItemRequest.setTypeId(1);

        ItemResponse itemResponse = itemService.updateItem(updateItemRequest, user.getUsername());
        if(itemResponse != null){
            assertEquals(user.getId(), itemResponse.getUserId());
            assertEquals(updateItemRequest.getItemId(), itemResponse.getId());
            assertEquals(itemResponse.getProduct().getPrice() * 2, itemResponse.getTotalPrice(), 0.1);
        }

    }
    @Test
    @Transactional
    public void addItemToCart() {
        CreateItemRequest createItemRequest = new CreateItemRequest();
        createItemRequest.setProductId(1);
        createItemRequest.setTypeId(1);
        createItemRequest.setQuantity(3);

        ItemResponse itemResponse = itemService.addItemToCart(createItemRequest, user.getUsername());
        assertEquals(user.getId(), itemResponse.getUserId());
        assertEquals(itemResponse.getProduct().getPrice() * itemResponse.getQuantity(), itemResponse.getTotalPrice(), 0.4);

    }

}
