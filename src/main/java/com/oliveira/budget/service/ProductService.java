package com.oliveira.budget.service;

import com.oliveira.budget.domain.product.CreateProductDTO;
import com.oliveira.budget.domain.product.Product;
import com.oliveira.budget.domain.user.User;
import com.oliveira.budget.repositories.ProductRepository;
import com.oliveira.budget.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    public CreateProductDTO createProduct(CreateProductDTO data) {

        Product product = new Product();

        if (data.name().length() > 100) {
            throw new IllegalArgumentException("Name is too long. Maximum length is 100");
        }

        product.setName(data.name());

        if (data.name().length() > 250) {
            throw new IllegalArgumentException("Description is too long. Maximum length is 250");
        }

        product.setDescription(data.description());
        product.setPrice(data.price());

        if (data.userId() == null) {
            throw new IllegalArgumentException("User is required");
        }

        User user = userRepository.findUserById(data.userId());

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        product.setUser(user);

        productRepository.save(product);

        return new CreateProductDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getUser().getId());
    }
}
