package com.oliveira.budget.domain.address;

import com.oliveira.budget.domain.client.Client;

public record RequestAddressDTO(String state, String city, String street, String number, Client client) {
}
