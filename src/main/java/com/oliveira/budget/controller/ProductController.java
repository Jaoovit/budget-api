package com.oliveira.budget.controller;

import com.oliveira.budget.domain.product.CreateProductDTO;
import com.oliveira.budget.domain.product.RequestProductDTO;
import com.oliveira.budget.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<CreateProductDTO> createProduct(@RequestBody CreateProductDTO data) {
        CreateProductDTO product = productService.createProduct(data);
        return ResponseEntity.ok(product);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RequestProductDTO> getProductById(@PathVariable UUID id) {
        RequestProductDTO product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }
}
