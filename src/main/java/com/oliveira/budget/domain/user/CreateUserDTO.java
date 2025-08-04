package com.oliveira.budget.domain.user;

import jakarta.validation.constraints.Email;

import java.util.UUID;

public record CreateUserDTO(UUID id, Email email, String name, String password) {
}
