package com.oliveira.budget.repositories;

import com.oliveira.budget.domain.budget.Budget;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BudgetRepository extends JpaRepository<Budget, UUID> {
}
