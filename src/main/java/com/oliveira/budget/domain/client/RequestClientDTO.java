package com.oliveira.budget.domain.client;

import java.util.UUID;

public record RequestClientDTO(String name,
                               String email,
                               String phone,
                               UUID userId,
                               String state,
                               String city,
                               String street,
                               String number) {
}
