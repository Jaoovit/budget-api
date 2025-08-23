package com.oliveira.budget.service;

import com.oliveira.budget.domain.product.CreateProductDTO;
import com.oliveira.budget.domain.product.Product;
import com.oliveira.budget.domain.user.User;
import com.oliveira.budget.repositories.ProductRepository;
import com.oliveira.budget.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<CreateProductDTO> createProduct(CreateProductDTO data) {

        Product product = new Product();

        product.setName(data.name());
        product.setDescription(data.description());
        product.setPrice(data.price());

        User user = userRepository.findUserById(data.userId());

        product.setUser(user);

        productRepository.save(product);

        CreateProductDTO createProductDTO = new CreateProductDTO(
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getUser().getId());

        return ResponseEntity.status(HttpStatus.CREATED).body(createProductDTO);
    }
}
