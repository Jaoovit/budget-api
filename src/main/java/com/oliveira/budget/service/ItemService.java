package com.oliveira.budget.service;

import com.oliveira.budget.domain.budget.Budget;
import com.oliveira.budget.domain.item.CreateItemDTO;
import com.oliveira.budget.domain.item.Item;
import com.oliveira.budget.domain.product.Product;
import com.oliveira.budget.repositories.BudgetRepository;
import com.oliveira.budget.repositories.ItemRepository;
import com.oliveira.budget.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private BudgetRepository budgetRepository;

    public CreateItemDTO createItem(CreateItemDTO data) {
        Item item = new Item();

        Product product = productRepository.findProductById(data.productId());

        if (product == null) {
            throw new IllegalArgumentException("Product not found");
        }

        Budget budget = budgetRepository.findBudgetById(data.budgetId());

        if (budget == null) {
            throw new IllegalArgumentException("Budget not found");
        }

        item.setQuantity(data.quantity());
        item.setProduct(product);
        item.setBudget(budget);

        itemRepository.save(item);

        return new CreateItemDTO(item.getQuantity(), item.getProduct().getId(), item.getBudget().getId());
    }
}
