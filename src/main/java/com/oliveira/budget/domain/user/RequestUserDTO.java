package com.oliveira.budget.domain.user;

import java.util.UUID;

public record RequestUserDTO(UUID id, String name, String email) {
}
