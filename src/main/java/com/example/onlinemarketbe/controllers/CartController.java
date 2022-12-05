package com.example.onlinemarketbe.controllers;

import com.example.onlinemarketbe.payload.request.CreateItemRequest;
import com.example.onlinemarketbe.payload.request.UpdateItemRequest;
import com.example.onlinemarketbe.payload.response.ItemResponse;
import com.example.onlinemarketbe.payload.response.MessageResponse;
import com.example.onlinemarketbe.services.ItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@CrossOrigin()
@RestController
@RequestMapping("/api/cart")

public class CartController {

    private final ItemService itemService;

    CartController(ItemService itemService){
        this.itemService = itemService;
    }

    @PostMapping("/add-item")
    public ResponseEntity<?> createItem(Principal principal, @RequestBody CreateItemRequest createItemRequest) {
        try {
            ItemResponse itemResponse = itemService.addItemToCart(createItemRequest, principal.getName());
            if (itemResponse == null) {
                return ResponseEntity.ok().body(new MessageResponse("Add fail!!!"));
            }
            return ResponseEntity.ok().body(new MessageResponse("Add success!!!"));
        } catch (Exception e) {
            return ResponseEntity.ok(e);
        }
    }
    @PutMapping("/update-item")
    public ResponseEntity<?> updateItem(Principal principal, @RequestBody UpdateItemRequest updateItemRequest) {
        try {
            ItemResponse itemResponse = itemService.updateItem(updateItemRequest, principal.getName());
            if (itemResponse == null) {
                return ResponseEntity.ok().body(new MessageResponse("Update fail!!!"));
            }
            return ResponseEntity.ok().body(new MessageResponse("Update success!!!"));
        } catch (Exception e) {
            return ResponseEntity.ok(e);
        }
    }
    @DeleteMapping("/delete-item/{id}")
    public ResponseEntity<?> deleteItem(Principal principal, @PathVariable int id) {
        try {
            boolean status = itemService.deleteItemById(id, principal.getName());
            if (!status) {
                return ResponseEntity.ok().body(new MessageResponse("Delete fail!!!"));
            }
            return ResponseEntity.ok().body(new MessageResponse("Delete success!!!"));
        } catch (Exception e) {
            return ResponseEntity.ok(e);
        }
    }
    @GetMapping("/get-item/{id}")
    public ResponseEntity<?> getItem(Principal principal, @PathVariable int id) {
        try {
            ItemResponse itemResponse = itemService.findItemById(id, principal.getName());
            if (itemResponse == null) {
                return ResponseEntity.ok().body(new MessageResponse("Item not exist!!!"));
            }
            return ResponseEntity.ok(itemResponse);
        } catch (Exception e) {
            return ResponseEntity.ok(e);
        }
    }
    @GetMapping("/get-item")
    public ResponseEntity<?> getItem(Principal principal) {
        try {
            List<ItemResponse> itemResponses = itemService.findAllItemByUser(principal.getName());
            if (itemResponses.size() <= 0) {
                return ResponseEntity.ok().body(new MessageResponse("You item empty!!!"));
            }
            return ResponseEntity.ok(itemResponses);
        } catch (Exception e) {
            return ResponseEntity.ok(e);
        }
    }
    @GetMapping("/get-item-order/{id}")
    public ResponseEntity<?> getItemOrder(Principal principal, @PathVariable int id) {
        try {
            List<ItemResponse> itemResponses = itemService.findAllItemByOrderId(id, principal.getName());
            if (itemResponses.size() <= 0) {
                return ResponseEntity.ok().body(new MessageResponse("Item in order not exist!!!"));
            }
            return ResponseEntity.ok(itemResponses);
        } catch (Exception e) {
            return ResponseEntity.ok(e);
        }
    }
}
