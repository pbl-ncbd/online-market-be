package com.example.onlinemarketbe.repositories;

import com.example.onlinemarketbe.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Integer> {
public Product findProductByIdAndStatus(int id,boolean status);
}
