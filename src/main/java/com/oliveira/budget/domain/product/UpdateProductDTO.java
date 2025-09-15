package com.oliveira.budget.domain.product;

import java.math.BigDecimal;

public record UpdateProductDTO(String name, String description, Float price) {
}
