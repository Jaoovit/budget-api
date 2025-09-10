package com.oliveira.budget.domain.budget;

import java.sql.Date;
import java.util.UUID;

public record CreateBudgetDTO(String name,
                              String description,
                              Date createdDate,
                              int monthValid,
                              Boolean approved,
                              Float totalPrice,
                              UUID clientId) {
}
