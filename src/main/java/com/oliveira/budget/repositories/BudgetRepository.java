package com.oliveira.budget.repositories;

import com.oliveira.budget.domain.budget.Budget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

public interface BudgetRepository extends JpaRepository<Budget, UUID> {

    @Query("SELECT e FROM Budget e WHERE e.id = :id")
    public Budget findBudgetById(@Param("id") UUID id);

    @Query("SELECT e FROM Budget e LEFT JOIN e.client a WHERE a.id = :clientId")
    public List<Budget> findBudgetByClientId(@Param("clientId") UUID clientId);

    @Transactional
    @Modifying
    @Query("UPDATE Budget e SET e.approved = :approved WHERE e.id = :id")
    public int approveBudget(@Param("id") UUID id, @Param("approved") Boolean approved);

    @Transactional
    @Modifying
    @Query("UPDATE Budget e SET e.name = :name, e.description = :description WHERE e.id = :id")
    public int updateBudget(@Param("id") UUID id, @Param("name") String name, @Param("description") String description);
}
