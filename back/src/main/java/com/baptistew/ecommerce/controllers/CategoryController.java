package com.baptistew.ecommerce.controllers;

import com.baptistew.ecommerce.model.Category;
import com.baptistew.ecommerce.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/")
    public ResponseEntity<List<Category>> getAll() {
        List<Category> body = categoryService.listCategories();
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Void> createCategory(@RequestBody Category category) {
        // Check if category does not already exist
        if (Objects.nonNull(categoryService.readCategoryByName(category.getName()))) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        categoryService.createCategory(category);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/{id}")
    public ResponseEntity<Void> updateCategory(@PathVariable Integer id, @RequestBody Category category) {
        if (categoryService.readCategoryById(id).isPresent()) {
            categoryService.updateCategory(id, category);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
