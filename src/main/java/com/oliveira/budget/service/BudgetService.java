package com.oliveira.budget.service;

import com.oliveira.budget.domain.budget.*;
import com.oliveira.budget.domain.client.Client;
import com.oliveira.budget.domain.item.ResponseItemDTO;
import com.oliveira.budget.domain.product.ResponseProductDTO;
import com.oliveira.budget.exception.InvalidInputException;
import com.oliveira.budget.exception.ResourceNotFoundException;
import com.oliveira.budget.repositories.BudgetRepository;
import com.oliveira.budget.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public RequestBudgetDTO createBudget(RequestBudgetDTO data) {
        Budget budget = new Budget();

        if (data.name().length() > 100) {
            throw new InvalidInputException("Name is too long. Maximum length is 100");
        }

        budget.setName(data.name());

        if (data.description().length() > 250) {
            throw new InvalidInputException("Description is too long. Maximum length is 250");
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
            throw new ResourceNotFoundException("Client not found");
        }

        budget.setClient(client);

        budgetRepository.save(budget);

        return new RequestBudgetDTO(budget.getName(),
                budget.getDescription(),
                budget.getCreatedDate(),
                budget.getValidDate(),
                data.monthValid(),
                budget.getApproved(),
                budget.getClient().getId());
    }

    public ResponseBudgetDTO getBudgetById(UUID id) {
        Budget budget = budgetRepository.findBudgetById(id);

        if (budget == null) {
            throw new ResourceNotFoundException("Budget not found");
        }

        float totalPrice = calculateTotalPrice(budget);

        return new ResponseBudgetDTO(
                budget.getId(),
                budget.getName(),
                budget.getDescription(),
                budget.getCreatedDate(),
                budget.getValidDate(),
                totalPrice,
                budget.getApproved()
        );
    }

    private float calculateTotalPrice(Budget budget) {
        List<ResponseItemDTO> items = itemService.getItemsByBudgetId(budget.getId());

        float totalPrice = 0F;

        for (ResponseItemDTO item : items) {
            ResponseProductDTO product = productService.getProductById(item.productId());
            totalPrice += product.price() * item.quantity();
        }

        return totalPrice;
    }

    public List<ResponseBudgetDTO> getBudgetByClientId(UUID clientId) {
        Client client = clientRepository.findClientById(clientId);

        if (client == null) {
            throw new ResourceNotFoundException("Client not found");
        }

        List<Budget> budgets = budgetRepository.findBudgetByClientId(client.getId());

        return budgets.stream()
                .map(budget -> getBudgetById(budget.getId()))
                .toList();
    }

    public ResponseBudgetDTO updateBudget(UpdateBudgetDTO data, UUID id) {
        Budget budget = budgetRepository.findBudgetById(id);

        if (budget == null) {
            throw new ResourceNotFoundException("Budget not found");
        }

        if (data.name().length() > 100) {
            throw new InvalidInputException("Name is too long. Maximum length is 100");
        }

        budget.setName(data.name());

        if (data.description().length() > 250) {
            throw new InvalidInputException("Description is too long. Maximum length is 250");
        }

        budget.setDescription(data.description());

        budgetRepository.updateBudget(id, data.name(), data.description());

        float totalPrice = calculateTotalPrice(budget);

        return new ResponseBudgetDTO(
                budget.getId(),
                budget.getName(),
                budget.getDescription(),
                budget.getCreatedDate(),
                budget.getValidDate(),
                totalPrice,
                budget.getApproved());
    }

    public ResponseBudgetDTO approvedBudget(UUID id) {
        Budget budget = budgetRepository.findBudgetById(id);

        if (budget == null) {
            throw new ResourceNotFoundException("Budget not found");
        }

        budget.setApproved(true);
        budgetRepository.save(budget);

        return getBudgetById(budget.getId());
    }

    public void deleteBudget(UUID id) {
        Budget budget = budgetRepository.findBudgetById(id);

        if (budget == null) {
            throw new ResourceNotFoundException("Budget not found");
        }

        budgetRepository.deleteById(budget.getId());
    }
}
