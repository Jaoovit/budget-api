package com.oliveira.budget.domain.client;

import com.oliveira.budget.domain.address.Address;

import java.util.UUID;

public record CreateClientDTO(String name,
                              String email,
                              String phone,
                              UUID userId,
                              String state,
                              String city,
                              String street,
                              String number) {
}
