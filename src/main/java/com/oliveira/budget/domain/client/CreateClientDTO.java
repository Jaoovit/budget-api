package com.oliveira.budget.domain.client;

import java.util.UUID;

public record CreateClientDTO(String name, String email, String phone, UUID userId) {
}
