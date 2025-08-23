package com.oliveira.budget.service;

import com.oliveira.budget.domain.product.CreateProductDTO;
import com.oliveira.budget.domain.product.Product;
import com.oliveira.budget.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public ResponseEntity createProduct(CreateProductDTO data) {

        Product product = new Product();

        product.setName(data.name());
        product.setDescription(data.description());
        product.setPrice(data.price());
        product.setUserId(data.userId());

        productRepository.save(product);

        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }
}
