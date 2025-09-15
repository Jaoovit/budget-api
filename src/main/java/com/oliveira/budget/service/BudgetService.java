package com.oliveira.budget.service;

import com.oliveira.budget.domain.budget.Budget;
import com.oliveira.budget.domain.budget.CreateBudgetDTO;
import com.oliveira.budget.domain.budget.RequestBudgetDTO;
import com.oliveira.budget.domain.client.Client;
import com.oliveira.budget.repositories.BudgetRepository;
import com.oliveira.budget.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class BudgetService {
    @Autowired
    private BudgetRepository budgetRepository;

    @Autowired
    private ClientRepository clientRepository;

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
}
