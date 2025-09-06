package com.oliveira.budget.repositories;

import com.oliveira.budget.domain.address.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface AddressRepository extends JpaRepository<Address, UUID> {

    @Query("SELECT e FROM Address e LEFT JOIN e.client a WHERE a.id = :clientId")
    public Address findAddressByClientId(@Param("clientId") UUID clientId);
}
