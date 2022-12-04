package com.example.onlinemarketbe.services;

import com.example.onlinemarketbe.model.Category;
import com.example.onlinemarketbe.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service

public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;
    public List<Category> getListCategory()
    {
        return categoryRepository.findAll();
    }
    public Category getCategoryById(int id)
    {
        return categoryRepository.findCategoryById(id);
    }
}
