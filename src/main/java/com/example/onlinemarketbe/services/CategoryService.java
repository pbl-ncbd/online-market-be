package com.example.onlinemarketbe.services;

import com.example.onlinemarketbe.model.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getListCategory();
    Category getCategoryById(int id);
}
