package com.oliveira.budget.repositories;

import com.oliveira.budget.domain.item.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

public interface ItemRepository extends JpaRepository<Item, UUID> {

    Item findItemById(UUID id);

    @Query("SELECT e FROM Item e LEFT JOIN e.budget a WHERE a.id = :budgetId")
    List<Item> findItemByBudgetId(@Param("budgetId") UUID budgetId);

    @Transactional
    @Modifying
    @Query("UPDATE Item e SET e.quantity = :quantity WHERE e.id = :id")
    void updateItemQuantity(@Param("id") UUID id, @Param("quantity") int quantity);
}
