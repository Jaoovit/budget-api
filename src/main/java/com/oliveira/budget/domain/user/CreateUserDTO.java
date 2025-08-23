package com.oliveira.budget.domain.user;

import java.util.List;
import java.util.UUID;

public record CreateUserDTO(String email, String name, String password, String confirmPassword) {
}
