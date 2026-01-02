package com.oliveira.budget.domain.user;

import java.util.UUID;

public record ResponseUserDTO(UUID id, String name, String email) {
}
