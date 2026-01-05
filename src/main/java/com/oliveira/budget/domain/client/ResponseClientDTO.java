package com.oliveira.budget.domain.client;

import com.oliveira.budget.domain.address.RequestAddressDTO;

import java.util.UUID;

public record ResponseClientDTO(UUID id,
                                String name,
                                String email,
                                String phone,
                                RequestAddressDTO address) {
}
