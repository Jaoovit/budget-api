package com.oliveira.budget.domain.budget;

import java.util.Date;
import java.util.UUID;

public record RequestBudgetDTO(String name,
                               String description,
                               Date createdDate,
                               Date validDate,
                               int monthValid,
                               Boolean approved,
                               UUID clientId) {
}
