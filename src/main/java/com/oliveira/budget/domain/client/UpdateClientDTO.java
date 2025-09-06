package com.oliveira.budget.domain.client;

import java.util.UUID;

public record UpdateClientDTO(String name,
                              String email,
                              String phone,
                              String state,
                              String city,
                              String street,
                              String number) {
}
