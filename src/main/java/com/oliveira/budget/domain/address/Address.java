package com.oliveira.budget.domain.address;

import com.oliveira.budget.domain.client.Client;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

import java.util.UUID;

public class Address {
    @Id
    @GeneratedValue
    private UUID id;

    private String state;
    private String city;
    private String street;
    private String number;

    @JoinColumn(name = "client_id")
    private Client client;
}
