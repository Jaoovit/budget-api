package com.oliveira.budget.repositories;

import com.oliveira.budget.domain.budget.Budget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface BudgetRepository extends JpaRepository<Budget, UUID> {

    @Query("SELECT e FROM Budget e WHERE e.id = :id")
    public Budget findBudgetById(@Param("id") UUID id);
}
