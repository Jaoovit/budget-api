package com.oliveira.budget.domain.product;

import java.util.UUID;

public record RequestProductDTO(UUID id, String name, String description, Float price, UUID userId) {
}
