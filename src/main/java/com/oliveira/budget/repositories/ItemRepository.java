package com.oliveira.budget.repositories;

import com.oliveira.budget.domain.item.Item;
import com.oliveira.budget.domain.item.RequestItemDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ItemRepository extends JpaRepository<Item, UUID> {

    @Query("SELECT e FROM Item e LEFT JOIN e.budget a WHERE a.id = :budgetId")
    public List<RequestItemDTO> findItemByBudgetId(@Param("budgetId") UUID budgetId);
}
