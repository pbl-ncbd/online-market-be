package com.example.onlinemarketbe.repositories;

import com.example.onlinemarketbe.model.AddressOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressOrderRepository extends JpaRepository<AddressOrder,Integer> {
    public AddressOrder findAddressOrderByOrderIdAndUserId(int id1,int id2);
    public AddressOrder findAddressOrderByOrderId(int id1);
}
