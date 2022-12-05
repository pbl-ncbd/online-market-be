package com.example.onlinemarketbe.services;

import com.example.onlinemarketbe.model.Category;
import com.example.onlinemarketbe.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service

public interface CategoryService {
    public List<Category> getListCategory();
    public Category getCategoryById(int id);
}
