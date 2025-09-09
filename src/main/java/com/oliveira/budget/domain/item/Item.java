package com.oliveira.budget.domain.item;

import com.oliveira.budget.domain.budget.Budget;
import com.oliveira.budget.domain.product.Product;
import jakarta.persistence.*;

import java.util.UUID;

@Table(name = "item")
public class Item {

    @Id
    @GeneratedValue
    private UUID id;

    private int quantity;

    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "budget_id")
    private Budget budget;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Budget getBudget() {
        return budget;
    }

    public void setBudget(Budget budget) {
        this.budget = budget;
    }
}
