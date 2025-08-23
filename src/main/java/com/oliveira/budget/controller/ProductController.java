package com.oliveira.budget.controller;

import com.oliveira.budget.domain.product.CreateProductDTO;
import com.oliveira.budget.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity createProduct(@RequestBody CreateProductDTO data) {
        return productService.createProduct(data);
    }
}
