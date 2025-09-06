package com.oliveira.budget.domain.client;

import com.oliveira.budget.domain.address.Address;

import java.util.UUID;

public record RequestClientDTO(UUID id,
                               String name,
                               String email,
                               String phone,
                               Address address) {
}
