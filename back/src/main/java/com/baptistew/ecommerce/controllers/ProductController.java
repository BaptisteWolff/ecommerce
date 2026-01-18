package com.baptistew.ecommerce.controllers;

import com.baptistew.ecommerce.dto.ProductDto;
import com.baptistew.ecommerce.model.Category;
import com.baptistew.ecommerce.model.Product;
import com.baptistew.ecommerce.repository.ProductRepository;
import com.baptistew.ecommerce.service.CategoryService;
import com.baptistew.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductRepository productRepository;

    @PostMapping("/")
    public ResponseEntity<Void> createProduct(@RequestBody ProductDto productDto) {
        Optional<Category> optionalCategory = categoryService.readCategoryById(productDto.getCategoryId());
        if (optionalCategory.isEmpty()) {
            return ResponseEntity.notFound().build();

        }

        Category category = optionalCategory.get();
        productService.addProduct(productDto, category);

        return ResponseEntity.created(URI.create("")).build();

    }

    @GetMapping("/")
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        List<ProductDto> productDtos = productService.findAllProducts();
        return ResponseEntity.ok(productDtos);
    }

    @PostMapping("/{productId}")
    public ResponseEntity<Void> updateProduct(@PathVariable("productId") Integer productId, @RequestBody ProductDto productDto) {
        Optional<Category> optionalCategory = categoryService.readCategoryById(productDto.getCategoryId());

        if (optionalCategory.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Category category = optionalCategory.get();
        productService.updateProduct(productId, productDto, category);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/delete/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);

        if (optionalProduct.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        productService.deleteProduct(productId);

        return ResponseEntity.ok().build();
    }
}
