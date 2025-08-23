package com.oliveira.budget.domain.product;

import java.math.BigDecimal;
import java.util.UUID;

public record CreateProductDTO(String name, String description, BigDecimal price, UUID userId) {
}
