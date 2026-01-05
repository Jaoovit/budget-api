package com.oliveira.budget.domain.item;

import java.util.UUID;

public record ResponseItemDTO(UUID id, int quantity, UUID productId, UUID budgetId) {
}
