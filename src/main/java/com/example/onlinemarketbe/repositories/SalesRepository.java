package com.example.onlinemarketbe.repositories;

import com.example.onlinemarketbe.model.EStatus;
import com.example.onlinemarketbe.model.Order;
import com.example.onlinemarketbe.model.Sales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SalesRepository extends JpaRepository<Sales,Integer> {
 @Query("SELECT T.order FROM Sales T WHERE T.user.id = :id and T.order.status.name = :eStatus ")
 public List<Order> findAllByUserIdAndStatus(int id, EStatus eStatus);

}
