package com.example.onlinemarketbe.controllers;

import com.example.onlinemarketbe.payload.request.CreateOrderRequest;
import com.example.onlinemarketbe.services.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@CrossOrigin()
@RestController
@RequestMapping("/api/order")
public class OrderBuyerController {

  private final OrderService orderService;

    public OrderBuyerController(OrderService orderService) {
        this.orderService = orderService;
    }


    @GetMapping("/get-payment")
    public ResponseEntity<?> getPayment()
    {
        try
        {
            return ResponseEntity.ok(orderService.getPayment());
        }catch (Exception e)
        {
            return ResponseEntity.ok(e);
        }
    }
    @GetMapping("/get-status")
    public ResponseEntity<?> getStatus()
    {
        try
        {
            return ResponseEntity.ok(orderService.getStatusAll());
        }catch (Exception e)
        {
            return ResponseEntity.ok(e);
        }
    }
    @PostMapping("/get-info-order")
    public ResponseEntity<?> getInfoOrder(Principal principal, @RequestBody CreateOrderRequest createOrderRequest)
    {
        try
        {
            return ResponseEntity.ok(orderService.getInfoOrder(principal.getName(),createOrderRequest));
        }catch (Exception e)
        {
            return ResponseEntity.ok(e);
        }
    }
    @PostMapping("/create-order")
    public ResponseEntity<?> createOrder(Principal principal, @RequestBody CreateOrderRequest createOrderRequest)
    {
        try
        {
            return ResponseEntity.ok(orderService.createOrder(principal.getName(),createOrderRequest));
        }catch (Exception e)
        {
            return ResponseEntity.ok(e);
        }
    }
    @GetMapping("/get-order-ordered")
    public ResponseEntity<?> getInfoOrderByOrdered(Principal principal)
    {
        try
        {
            return ResponseEntity.ok(orderService.getInfoOrderByOrdered(principal.getName()));
        }catch (Exception e)
        {
            return ResponseEntity.ok(e);
        }
    }
    @GetMapping("/get-order-transport")
    public ResponseEntity<?> getInfoOrderByTransport(Principal principal)
    {
        try
        {
            return ResponseEntity.ok(orderService.getInfoOrderByTransport(principal.getName()));
        }catch (Exception e)
        {
            return ResponseEntity.ok(e);
        }
    }
    @GetMapping("/get-order-received")
    public ResponseEntity<?> getInfoOrderByReceived(Principal principal)
    {
        try
        {
            return ResponseEntity.ok(orderService.getInfoOrderByReceived(principal.getName()));
        }catch (Exception e)
        {
            return ResponseEntity.ok(e);
        }
    }



}
