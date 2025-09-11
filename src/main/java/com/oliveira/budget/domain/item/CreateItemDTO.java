package com.oliveira.budget.domain.item;

import java.util.UUID;

public record CreateItemDTO(int quantity, UUID productId, UUID budgetId) {
}
