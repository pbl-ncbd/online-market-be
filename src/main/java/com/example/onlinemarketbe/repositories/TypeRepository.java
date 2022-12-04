package com.example.onlinemarketbe.repositories;

import com.example.onlinemarketbe.model.Type;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TypeRepository extends JpaRepository<Type,Integer> {
public List<Type> findAllByProductId(int id);
}
