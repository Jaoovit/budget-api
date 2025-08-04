package com.oliveira.budget.domain.user;

import java.util.UUID;

public record CreateUserDTO(UUID id, String email, String name, String password, String confirmPassword) {
}
