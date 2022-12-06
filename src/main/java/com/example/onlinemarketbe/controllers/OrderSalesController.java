package com.example.onlinemarketbe.controllers;

import com.example.onlinemarketbe.payload.request.CreateOrderRequest;
import com.example.onlinemarketbe.payload.request.UpdateStatusOrder;
import com.example.onlinemarketbe.services.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@CrossOrigin()
@RestController
@RequestMapping("/api/sale")
@PreAuthorize ("hasRole('ROLE_SELLER')")
public class OrderSalesController {
    private final OrderService orderService;

    public OrderSalesController(OrderService orderService) {
        this.orderService = orderService;
    }


    @GetMapping("/get-info-order-ordered")
    public ResponseEntity<?> getInfoOrderByOrderedSales(Principal principal)
    {
        try
        {
            return ResponseEntity.ok(orderService.getInfoOrderByOrderedSales(principal.getName()));
        }catch (Exception e)
        {
            return ResponseEntity.ok(e);
        }
    }

    @GetMapping("/get-info-order-transport")
    public ResponseEntity<?> getInfoOrder(Principal principal)
    {
        try
        {
            return ResponseEntity.ok(orderService.getInfoOrderByTransportSales(principal.getName()));
        }catch (Exception e)
        {
            return ResponseEntity.ok(e);
        }
    }
    @GetMapping("/get-info-order-received")
    public ResponseEntity<?> getInfoOrderByReceivedSales(Principal principal)
    {
        try
        {
            return ResponseEntity.ok(orderService.getInfoOrderByReceivedSales(principal.getName()));
        }catch (Exception e)
        {
            return ResponseEntity.ok(e);
        }
    }
   @PostMapping("/update-status")
    public ResponseEntity<?> updateStatus(Principal principal, UpdateStatusOrder updateStatusOrder)
   {
       try
       {
           return ResponseEntity.ok(orderService.updateStatusSales(principal.getName(),updateStatusOrder));
       }catch (Exception e)
       {
           return ResponseEntity.ok(e);
       }

   }

}
