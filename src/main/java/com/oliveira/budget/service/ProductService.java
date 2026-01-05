package com.oliveira.budget.service;

import com.oliveira.budget.domain.product.RequestProductDTO;
import com.oliveira.budget.domain.product.Product;
import com.oliveira.budget.domain.product.ResponseProductDTO;
import com.oliveira.budget.domain.product.UpdateProductDTO;
import com.oliveira.budget.domain.user.User;
import com.oliveira.budget.exception.InvalidInputException;
import com.oliveira.budget.exception.ResourceNotFoundException;
import com.oliveira.budget.repositories.ProductRepository;
import com.oliveira.budget.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    public RequestProductDTO createProduct(RequestProductDTO data) {

        Product product = new Product();

        if (data.name().length() > 100) {
            throw new InvalidInputException("Name is too long. Maximum length is 100");
        }

        product.setName(data.name());

        if (data.description().length() > 250) {
            throw new InvalidInputException("Description is too long. Maximum length is 250");
        }

        product.setDescription(data.description());
        product.setPrice(data.price());

        if (data.userId() == null) {
            throw new InvalidInputException("User is required");
        }

        User user = userRepository.findUserById(data.userId());

        if (user == null) {
            throw new ResourceNotFoundException("User not found");
        }

        product.setUser(user);

        productRepository.save(product);

        return new RequestProductDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getUser().getId());
    }

    public ResponseProductDTO getProductById(UUID id) {
        Product product = productRepository.findProductById(id);

        if (product == null) {
            throw new ResourceNotFoundException("Product not found");
        }

        return new ResponseProductDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice()
        );
    }

    public List<ResponseProductDTO> getProductsByUserID(int page, int size, UUID userId) {

        Pageable pageable = PageRequest.of(page, size);

        Page<Product> productPage = productRepository.findProductsByUserId(userId, pageable);

        return productPage.map(product -> new ResponseProductDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice())
        ).stream().toList();
    }

    public List<ResponseProductDTO> searchProducts(int page, int size, UUID userId, String search) {
        Pageable pageable = PageRequest.of(page, size);

        Page<Product> productPage = productRepository.searchProducts(userId, search, pageable);

        return productPage.map(product -> new ResponseProductDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice())
        ).stream().toList();
    }

    public ResponseProductDTO updateProduct(UUID id, UpdateProductDTO data) {
        Product product = productRepository.findProductById(id);

        if (product == null) {
            throw new ResourceNotFoundException("Product not found");
        }

        product.setName(data.name());

        if (data.name().length() > 100) {
            throw new InvalidInputException("Name is too long. Maximum length is 100");
        }

        product.setDescription(data.description());

        if (data.name().length() > 250) {
            throw new InvalidInputException("Description is too long. Maximum length is 250");
        }

        product.setPrice(data.price());

        productRepository.updateProduct(id, data.name(), data.description(), data.price());

        return new ResponseProductDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice()
        );
    }

    public void deleteProduct(UUID id) {
        Product product = productRepository.findProductById(id);

        if (product == null) {
            throw new ResourceNotFoundException("Product not found");
        }

        productRepository.deleteById(product.getId());
    }
}
