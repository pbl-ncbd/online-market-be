package com.example.onlinemarketbe.repositories;

import com.example.onlinemarketbe.model.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TypeRepository extends JpaRepository<Type,Integer> {
public List<Type> findAllByProductId(int id);
public Type findTypeById(int id);
}
