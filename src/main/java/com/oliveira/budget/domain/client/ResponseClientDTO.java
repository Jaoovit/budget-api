package com.oliveira.budget.domain.client;

import com.oliveira.budget.domain.address.Address;

import java.util.UUID;

public record ResponseClientDTO(String name, String email, String phone, UUID userId) {
}
