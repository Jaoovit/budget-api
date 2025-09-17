package com.oliveira.budget.service;

import com.oliveira.budget.domain.budget.Budget;
import com.oliveira.budget.domain.item.ChangeQuantityDTO;
import com.oliveira.budget.domain.item.CreateItemDTO;
import com.oliveira.budget.domain.item.Item;
import com.oliveira.budget.domain.item.RequestItemDTO;
import com.oliveira.budget.domain.product.Product;
import com.oliveira.budget.exception.ResourceNotFoundException;
import com.oliveira.budget.repositories.BudgetRepository;
import com.oliveira.budget.repositories.ItemRepository;
import com.oliveira.budget.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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
            throw new ResourceNotFoundException("Product not found");
        }

        Budget budget = budgetRepository.findBudgetById(data.budgetId());

        if (budget == null) {
            throw new ResourceNotFoundException("Budget not found");
        }

        item.setQuantity(data.quantity());
        item.setProduct(product);
        item.setBudget(budget);

        itemRepository.save(item);

        return new CreateItemDTO(item.getQuantity(), item.getProduct().getId(), item.getBudget().getId());
    }

    public List<RequestItemDTO> getItemsByBudgetId(UUID budgetId) {
        Budget budget = budgetRepository.findBudgetById(budgetId);

        if (budget == null) {
            throw new ResourceNotFoundException("Budget not found");
        }

        List<Item> items = itemRepository.findItemByBudgetId(budget.getId());

        return items.stream()
           .map(item -> new RequestItemDTO(
                   item.getId(),
                   item.getQuantity(),
                   item.getProduct().getId(),
                   item.getBudget().getId()))
                .collect(Collectors.toList());
    }

    public RequestItemDTO updateItemQuantity(UUID id, ChangeQuantityDTO data) {
        Item item = itemRepository.getReferenceById(id);

        if (item == null) {
            throw new ResourceNotFoundException("Item not found");
        }

        item.setQuantity(data.quantity());

        itemRepository.updateItemQuantity(id, data.quantity());

        return new RequestItemDTO(
                item.getId(),
                item.getQuantity(),
                item.getProduct().getId(),
                item.getBudget().getId()
        );
    }

    public void deleteItem(UUID id) {
        Item item = itemRepository.getReferenceById(id);

        if (item == null) {
            throw new ResourceNotFoundException("Item not found");
        }

        itemRepository.deleteById(item.getId());
    }
}
