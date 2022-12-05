package com.example.onlinemarketbe.repositories;

import com.example.onlinemarketbe.model.EStatus;
import com.example.onlinemarketbe.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Integer> {
    @Query("SELECT T FROM Order T WHERE T.buyer.id = :id and T.status.name = :eStatus ")
    public List<Order> findAllByBuyerIdAndStatus(int id,EStatus eStatus);
    public  Order findOrderById(int id);


}
