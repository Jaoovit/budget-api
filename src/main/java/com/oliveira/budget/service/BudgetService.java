package com.oliveira.budget.service;

import com.oliveira.budget.domain.budget.*;
import com.oliveira.budget.domain.client.Client;
import com.oliveira.budget.domain.item.RequestItemDTO;
import com.oliveira.budget.domain.product.RequestProductDTO;
import com.oliveira.budget.repositories.BudgetRepository;
import com.oliveira.budget.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class BudgetService {
    @Autowired
    private BudgetRepository budgetRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ItemService itemService;

    @Autowired
    private ProductService productService;

    public RequestBudgetDTO createBudget(CreateBudgetDTO data) {
        Budget budget = new Budget();

        if (data.name().length() > 100) {
            throw new IllegalArgumentException("Name is too long. Maximum length is 100");
        }

        budget.setName(data.name());

        if (data.name().length() > 250) {
            throw new IllegalArgumentException("Description is too long. Maximum length is 250");
        }

        budget.setDescription(data.description());

        Date createdAt = new Date();

        budget.setCreatedDate(createdAt);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(createdAt);
        calendar.add(Calendar.MONTH, data.monthValid());

        budget.setValidDate(calendar.getTime());

        budget.setApproved(false);

        Client client = clientRepository.findClientById(data.clientId());

        if (client == null) {
            throw new IllegalArgumentException("Client not found");
        }

        budget.setClient(client);

        budgetRepository.save(budget);

        return new RequestBudgetDTO(budget.getName(),
                budget.getDescription(),
                budget.getCreatedDate(),
                budget.getValidDate(),
                budget.getApproved(),
                budget.getClient().getId());
    }

    public GetBudgetDTO getBudgetById(UUID id) {
        Budget budget = budgetRepository.findBudgetById(id);

        if (budget == null) {
            throw new IllegalArgumentException("Budget not found");
        }

        List<RequestItemDTO> items = itemService.getItemsByBudgetId(budget.getId());

        Float totalPrice = 0F;

        for (RequestItemDTO item : items) {
            RequestProductDTO product = productService.getProductById(item.productId());
            totalPrice += product.price() * item.quantity();
        }

        return new GetBudgetDTO(
                budget.getId(),
                budget.getName(),
                budget.getDescription(),
                budget.getCreatedDate(),
                budget.getValidDate(),
                totalPrice,
                budget.getApproved()
                );
    }

    public RequestBudgetDTO updateBudget(UpdateBudgetDTO data, UUID id) {
        Budget budget = budgetRepository.findBudgetById(id);

        if (budget == null) {
            throw new IllegalArgumentException("Budget not found");
        }

        if (data.name().length() > 100) {
            throw new IllegalArgumentException("Name is too long. Maximum length is 100");
        }

        budget.setName(data.name());

        if (data.name().length() > 250) {
            throw new IllegalArgumentException("Description is too long. Maximum length is 250");
        }

        budget.setDescription(data.description());

        budgetRepository.updateBudget(id, data.name(), data.description());

        return new RequestBudgetDTO(budget.getName(),
                budget.getDescription(),
                budget.getCreatedDate(),
                budget.getValidDate(),
                budget.getApproved(),
                budget.getClient().getId());
    }

    public GetBudgetDTO approvedBudget(UUID id) {
        Budget budget = budgetRepository.findBudgetById(id);

        if (budget == null) {
            throw new IllegalArgumentException("Budget not found");
        }

        budget.setApproved(true);
        budgetRepository.save(budget);

        return getBudgetById(budget.getId());
    }

    public void deleteBudget(UUID id) {
        Budget budget = budgetRepository.findBudgetById(id);

        if (budget == null) {
            throw new IllegalArgumentException("Budget not found");
        }

        budgetRepository.deleteById(budget.getId());
    }
}
