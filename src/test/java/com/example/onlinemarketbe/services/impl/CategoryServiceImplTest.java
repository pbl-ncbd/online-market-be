package com.example.onlinemarketbe.services.impl;

import com.example.onlinemarketbe.model.Category;
import com.example.onlinemarketbe.repositories.CategoryRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoryServiceImplTest {
    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryServiceImpl categoryService;


    @Test
    @DisplayName("Should return the category when the id is valid")
    void getCategoryByIdWhenIdIsValid() {
        Category category = new Category();
        category.setId(1);
        category.setName("category");
        category.setStatus(true);

        when(categoryRepository.findCategoryById(1)).thenReturn(category);

        Category result = categoryService.getCategoryById(1);

        assertEquals(1, result.getId());
        assertEquals("category", result.getName());
        assertTrue(result.isStatus());
    }

    @Test
    @DisplayName("Should return all categories")
    void getListCategoryShouldReturnAllCategories() {
        List<Category> categories = new ArrayList<>();
        categories.add(new Category());
        categories.add(new Category());
        when(categoryRepository.findAll()).thenReturn(categories);
        List<Category> categorieList = categoryRepository.findAll();

        assertEquals(2, categorieList.size());
    }
}