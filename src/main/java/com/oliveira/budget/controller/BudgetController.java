package com.oliveira.budget.controller;

import com.oliveira.budget.domain.budget.Budget;
import com.oliveira.budget.domain.budget.CreateBudgetDTO;
import com.oliveira.budget.service.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/budgets")
public class BudgetController {

    @Autowired
    private BudgetService budgetService;

    @PostMapping
    public ResponseEntity<Budget> createBudget(@RequestBody CreateBudgetDTO data) {
        Budget budget = budgetService.createBudGet(data);
        return ResponseEntity.ok(budget);
    }
}
