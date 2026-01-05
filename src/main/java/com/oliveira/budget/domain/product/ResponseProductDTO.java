package com.oliveira.budget.domain.product;

import java.util.UUID;

public record ResponseProductDTO(UUID id, String name, String description, Float price) {
}
