package com.oliveira.budget.domain.user;

public record RequestUserDTO(String email, String name, String password, String confirmPassword) {
}
