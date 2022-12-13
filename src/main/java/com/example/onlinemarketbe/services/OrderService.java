package com.example.onlinemarketbe.services;

import com.example.onlinemarketbe.model.Payment;
import com.example.onlinemarketbe.model.Status;
import com.example.onlinemarketbe.payload.request.CreateOrderRequest;
import com.example.onlinemarketbe.payload.request.UpdateStatusOrder;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface OrderService {
    ResponseEntity<?> createOrder(String username, CreateOrderRequest createOrderRequest);
    ResponseEntity<?> getInfoOrder(String username, CreateOrderRequest createOrderRequest);
    ResponseEntity<?> getInfoOrderByOrdered(String username);
    ResponseEntity<?> getInfoOrderByTransport(String username);
    ResponseEntity<?> getInfoOrderByReceived(String username);
    ResponseEntity<?> getInfoOrderByOrderedSales(String username);
    ResponseEntity<?> getInfoOrderByTransportSales(String username);
    ResponseEntity<?> getInfoOrderByReceivedSales(String username);
    ResponseEntity<?> updateStatusSales(String username, UpdateStatusOrder updateStatusOrder);
    List<Payment> getPayment();
    List<Status> getStatusAll();






}
