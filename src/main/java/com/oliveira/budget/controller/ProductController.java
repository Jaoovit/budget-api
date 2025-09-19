package com.oliveira.budget.controller;

import com.oliveira.budget.domain.product.CreateProductDTO;
import com.oliveira.budget.domain.product.RequestProductDTO;
import com.oliveira.budget.domain.product.UpdateProductDTO;
import com.oliveira.budget.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<RequestProductDTO>> getProductsByUserId(
            @PathVariable UUID userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        List<RequestProductDTO> products = productService.getProductsByUserID(page, size, userId);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/search/user/{userId}")
    public ResponseEntity<List<RequestProductDTO>> searchProducts(@PathVariable UUID userId,
                                                                  @RequestParam(defaultValue = "0") int page,
                                                                  @RequestParam(defaultValue = "10") int size,
                                                                  @RequestParam String search) {
        List<RequestProductDTO> products = productService.searchProducts(page, size, userId, search);
        return ResponseEntity.ok(products);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RequestProductDTO> updateProduct(@PathVariable UUID id, @RequestBody UpdateProductDTO data) {
        RequestProductDTO product = productService.updateProduct(id, data);
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteProduct(@PathVariable UUID id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }
}
