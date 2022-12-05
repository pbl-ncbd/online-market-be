package com.example.onlinemarketbe.repositories;

import com.example.onlinemarketbe.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
public Category findCategoryById(int id);

}