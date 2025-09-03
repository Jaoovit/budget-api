package com.oliveira.budget.repositories;

import com.oliveira.budget.domain.client.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClientRepository extends JpaRepository<Client, UUID> {
}
