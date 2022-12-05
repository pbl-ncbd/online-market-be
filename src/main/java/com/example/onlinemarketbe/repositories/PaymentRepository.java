package com.example.onlinemarketbe.repositories;

import com.example.onlinemarketbe.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment,Integer> {
    public  Payment findPaymentById(int id);
}
