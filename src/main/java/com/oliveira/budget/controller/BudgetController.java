package com.oliveira.budget.controller;

import com.oliveira.budget.domain.budget.ResponseBudgetDTO;
import com.oliveira.budget.domain.budget.RequestBudgetDTO;
import com.oliveira.budget.domain.budget.UpdateBudgetDTO;
import com.oliveira.budget.service.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/budgets")
public class BudgetController {

    @Autowired
    private BudgetService budgetService;

    @PostMapping
    public ResponseEntity<RequestBudgetDTO> createBudget(@RequestBody RequestBudgetDTO data) {
        RequestBudgetDTO budget = budgetService.createBudget(data);
        return ResponseEntity.ok(budget);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseBudgetDTO> getBudgetById(@PathVariable UUID id) {
        ResponseBudgetDTO budget = budgetService.getBudgetById(id);
        return ResponseEntity.ok(budget);
    }

    @GetMapping("/clients/{clientId}")
    public ResponseEntity<List<ResponseBudgetDTO>> getBudgetsByClientId(@PathVariable UUID clientId) {
        List<ResponseBudgetDTO> budgets = budgetService.getBudgetByClientId(clientId);
        return ResponseEntity.ok(budgets);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseBudgetDTO> updateBudget(@PathVariable UUID id, @RequestBody UpdateBudgetDTO data) {
        ResponseBudgetDTO budget = budgetService.updateBudget(data, id);
        return ResponseEntity.ok(budget);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ResponseBudgetDTO> approveBudget(@PathVariable UUID id) {
        ResponseBudgetDTO budget = budgetService.approvedBudget(id);
        return ResponseEntity.ok(budget);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteBudget(@PathVariable UUID id) {
        budgetService.deleteBudget(id);
        return ResponseEntity.ok().build();
    }
}
