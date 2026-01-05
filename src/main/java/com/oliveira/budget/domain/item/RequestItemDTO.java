package com.oliveira.budget.domain.item;

import java.util.UUID;

public record RequestItemDTO(int quantity, UUID productId, UUID budgetId) {
}
