package com.oliveira.budget.service;

import com.oliveira.budget.domain.product.CreateProductDTO;
import com.oliveira.budget.domain.product.Product;
import com.oliveira.budget.domain.product.RequestProductDTO;
import com.oliveira.budget.domain.product.UpdateProductDTO;
import com.oliveira.budget.domain.user.User;
import com.oliveira.budget.repositories.ProductRepository;
import com.oliveira.budget.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

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

    public RequestProductDTO getProductById(UUID id) {
        Product product = productRepository.findProductById(id);

        if (product == null) {
            throw new IllegalArgumentException("Product not found");
        }

        return new RequestProductDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice()
        );
    }

    public List<RequestProductDTO> getProductsByUserID(int page, int size, UUID userId) {

        Pageable pageable = PageRequest.of(page, size);

        Page<Product> productPage = productRepository.findProductsByUserId(userId, pageable);

        return productPage.map(product -> new RequestProductDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice())
        ).stream().toList();
    }

    public RequestProductDTO updateProduct(UUID id, UpdateProductDTO data) {
        Product product = productRepository.findProductById(id);

        if (product == null) {
            throw new IllegalArgumentException("Product not found");
        }

        product.setName(data.name());

        if (data.name().length() > 100) {
            throw new IllegalArgumentException("Name is too long. Maximum length is 100");
        }

        product.setDescription(data.description());

        if (data.name().length() > 250) {
            throw new IllegalArgumentException("Description is too long. Maximum length is 250");
        }

        product.setPrice(data.price());

        productRepository.updateProduct(id, data.name(), data.description(), data.price());

        return new RequestProductDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice()
        );
    }
}
