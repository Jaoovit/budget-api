package com.oliveira.budget.domain.auth;

import java.util.UUID;

public record TokenDTO(String token, UUID userId) {
}
