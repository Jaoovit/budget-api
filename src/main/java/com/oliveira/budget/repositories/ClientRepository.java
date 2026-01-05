package com.oliveira.budget.repositories;

import com.oliveira.budget.domain.client.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public interface ClientRepository extends JpaRepository<Client, UUID> {

    @Query("SELECT e FROM Client e LEFT JOIN e.user a WHERE a.id = :userId")
    public Page<Client> findClientsByUserId(@Param("userId") UUID userId, Pageable pageable);

    @Transactional
    @Modifying
    @Query("UPDATE Client e SET e.name = :name, e.email = :email, e.phone = :phone WHERE e.id = :id")
    public int updateClient(@Param("id") UUID id,
                            @Param("name") String name,
                            @Param("email") String email,
                            @Param("phone") String phone);

    public Client findClientById(@Param("id") UUID id);
}
