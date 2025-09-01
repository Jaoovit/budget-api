package com.oliveira.budget.repositories;

import com.oliveira.budget.domain.product.Product;
import com.oliveira.budget.domain.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {

    @Query("SELECT e FROM Product e WHERE e.id = :id")
    public Product findProductById(@Param("id") UUID id);

    @Query("SELECT e FROM Product e LEFT JOIN e.user a WHERE a.id = :userId")
    public Page<Product> findProductsByUserId(@Param("userId") UUID id, Pageable pageable);

    @Modifying
    @Query("UPDATE Product e SET e.name = :name, e.description = :description," +
           "e.price = :price WHERE e.id = :id")
    public Product updateProduct(@Param("id") UUID id,
                                 @Param("name") String name,
                                 @Param("description") String description,
                                 @Param("price") BigDecimal price);
}
