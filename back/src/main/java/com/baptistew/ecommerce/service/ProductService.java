package com.baptistew.ecommerce.service;

import com.baptistew.ecommerce.dto.ProductDto;
import com.baptistew.ecommerce.model.Category;
import com.baptistew.ecommerce.model.Product;
import com.baptistew.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public void addProduct(ProductDto productDto, Category category) {
        Product product = new Product();
        product.setCategory(category);
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());
        product.setImageUrl(productDto.getImageUrl());

        productRepository.save(product);
    }

    public List<ProductDto> findAllProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductDto> productDtos = new ArrayList<>();

        for (Product product : products) {
            productDtos.add(new ProductDto(product));
        }
        return productDtos;
    }

    public void updateProduct(Integer productId, ProductDto productDto, Category category) {
        Product product = new Product();

        product.setId(productId);
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());
        product.setImageUrl(productDto.getImageUrl());

        productRepository.save(product);
    }

    public void deleteProduct(Integer productId) {
        productRepository.deleteById(productId);
    }
}
