package com.example.onlinemarketbe.repositories;

import com.example.onlinemarketbe.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Integer> {
public Product findProductByIdAndStatus(int id,boolean status);
public List<Product> findAllByStatus(boolean status);
public List<Product> findAllByUserIdAndStatus(int id, boolean status);
}
