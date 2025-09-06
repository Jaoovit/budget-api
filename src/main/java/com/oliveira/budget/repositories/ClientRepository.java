package com.oliveira.budget.repositories;

import com.oliveira.budget.domain.client.Client;
import com.oliveira.budget.domain.client.RequestClientDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface ClientRepository extends JpaRepository<Client, UUID> {

    @Query("SELECT e FROM Client e LEFT JOIN e.user a WHERE a.id = :userId")
    public Page<Client> findClientsByUserId(@Param("userId") UUID userId, Pageable pageable);
}
