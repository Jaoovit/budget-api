package com.oliveira.budget.controller;

import com.oliveira.budget.domain.budget.CreateBudgetDTO;
import com.oliveira.budget.domain.budget.GetBudgetDTO;
import com.oliveira.budget.domain.budget.RequestBudgetDTO;
import com.oliveira.budget.domain.budget.UpdateBudgetDTO;
import com.oliveira.budget.service.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/budgets")
public class BudgetController {

    @Autowired
    private BudgetService budgetService;

    @PostMapping
    public ResponseEntity<RequestBudgetDTO> createBudget(@RequestBody CreateBudgetDTO data) {
        RequestBudgetDTO budget = budgetService.createBudget(data);
        return ResponseEntity.ok(budget);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetBudgetDTO> getBudgetById(@PathVariable UUID id) {
        GetBudgetDTO budget = budgetService.getBudgetById(id);
        return ResponseEntity.ok(budget);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RequestBudgetDTO> updateBudget(@PathVariable UUID id, @RequestBody UpdateBudgetDTO data) {
        RequestBudgetDTO budget = budgetService.updateBudget(data, id);
        return ResponseEntity.ok(budget);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<GetBudgetDTO> approveBudget(@PathVariable UUID id) {
        GetBudgetDTO budget = budgetService.approvedBudget(id);
        return ResponseEntity.ok(budget);
    }
}
