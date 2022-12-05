package com.example.onlinemarketbe.services.impl;

import com.example.onlinemarketbe.model.Category;
import com.example.onlinemarketbe.repositories.CategoryRepository;
import com.example.onlinemarketbe.services.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    @Override
    public List<Category> getListCategory()
    {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategoryById(int id)
    {
        return categoryRepository.findCategoryById(id);
    }
}
