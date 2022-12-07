package com.example.onlinemarketbe.repositories;

import com.example.onlinemarketbe.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {


    @Query("SELECT p FROM Product p WHERE (lower(p.name) LIKE %:keyword%" + " OR lower(p.category.name) LIKE %:keyword% ) AND (p.status <>false ) AND( p.category.status <>false )" )
    public List<Product> searchProduct(String keyword);

    @Query("SELECT p FROM Product p WHERE p.status <>false AND p.category.id = :id" )
    public List<Product> findProductByCategoryId(int id);
    public Product findProductByIdAndStatus(int id,boolean status);

    public List<Product> findAllByStatus(boolean status);
    public List<Product> findAllByUserIdAndStatus(int id, boolean status);

}
