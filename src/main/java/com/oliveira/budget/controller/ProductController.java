package com.oliveira.budget.controller;

import com.oliveira.budget.domain.product.RequestProductDTO;
import com.oliveira.budget.domain.product.ResponseProductDTO;
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
    public ResponseEntity<RequestProductDTO> createProduct(@RequestBody RequestProductDTO data) {
        RequestProductDTO product = productService.createProduct(data);
        return ResponseEntity.ok(product);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseProductDTO> getProductById(@PathVariable UUID id) {
        ResponseProductDTO product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ResponseProductDTO>> getProductsByUserId(
            @PathVariable UUID userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        List<ResponseProductDTO> products = productService.getProductsByUserID(page, size, userId);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/search/user/{userId}")
    public ResponseEntity<List<ResponseProductDTO>> searchProducts(@PathVariable UUID userId,
                                                                   @RequestParam(defaultValue = "0") int page,
                                                                   @RequestParam(defaultValue = "10") int size,
                                                                   @RequestParam String search) {
        List<ResponseProductDTO> products = productService.searchProducts(page, size, userId, search);
        return ResponseEntity.ok(products);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseProductDTO> updateProduct(@PathVariable UUID id, @RequestBody UpdateProductDTO data) {
        ResponseProductDTO product = productService.updateProduct(id, data);
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteProduct(@PathVariable UUID id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }
}
