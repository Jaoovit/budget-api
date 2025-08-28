package com.oliveira.budget.repositories;

import com.oliveira.budget.domain.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {

    @Query("SELECT e FROM Product e WHERE e.id = :id")
    public Product findProductById(@Param("id") UUID id);
}
