package com.baptistew.ecommerce.service;

import com.baptistew.ecommerce.model.Category;
import com.baptistew.ecommerce.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> listCategories() {
        return categoryRepository.findAll();
    }

    public void createCategory(Category category) {
        categoryRepository.save(category);
    }

    public Category readCategoryByName(String categoryName) {
        return categoryRepository.findByName(categoryName);
    }

    public Optional<Category> readCategoryById(Integer categoryId) {
        return categoryRepository.findById(categoryId);
    }

    public void updateCategory(Integer categoryId, Category newCategory) {
        Category category = categoryRepository.findById(categoryId).get();
        category.setName(newCategory.getName());
        category.setDescription(newCategory.getDescription());
        category.setImageUrl(newCategory.getImageUrl());
        categoryRepository.save(category);
    }
}
