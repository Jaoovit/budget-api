package com.oliveira.budget.domain.budget;

import java.util.Date;
import java.util.UUID;

public record GetBudgetDTO(UUID id,
                           String name,
                           String description,
                           Date createdDate,
                           Date validDate,
                           Float totalPrice,
                           Boolean approved) {
}
