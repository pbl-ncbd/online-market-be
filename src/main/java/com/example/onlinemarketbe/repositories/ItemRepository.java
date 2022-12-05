package com.example.onlinemarketbe.repositories;

import com.example.onlinemarketbe.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {

    Item findItemByIdAndOrderId(Integer id, Integer orderId);
    Item findItemByIdAndUserIdAndOrderId(Integer id, Integer userId, Integer orderId);
    Item findItemByProductIdAndTypeIdAndUserIdAndOrderId(Integer productId, Integer typeId, Integer userId, Integer orderId);

    List<Item> findAllByUserIdAndOrderId(Integer userId, Integer orderId);
    List<Item> findAllByOrderId(int id);
    @Query("SELECT T FROM Item T WHERE T.product.user.id = :idUser and T.id=:idItem")
      Item findItemBySale(int idUser,int idItem);
    Item findItemById(int id);


}

