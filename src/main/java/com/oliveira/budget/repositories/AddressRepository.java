package com.oliveira.budget.repositories;

import com.oliveira.budget.domain.address.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public interface AddressRepository extends JpaRepository<Address, UUID> {

    @Query("SELECT e FROM Address e LEFT JOIN e.client a WHERE a.id = :clientId")
    Address findAddressByClientId(@Param("clientId") UUID clientId);

    @Transactional
    @Modifying
    @Query("UPDATE Address e SET e.state = :state, e.city = :city, e.street = :street, " +
            " e.number = :number WHERE e.id = :id")
    void updateAddress(@Param("id") UUID id,
                             @Param("state") String state,
                             @Param("city") String city,
                             @Param("street") String street,
                             @Param("number") String number);

    @Query("SELECT e FROM Address e WHERE e.id = :id")
    Address findAddressById(@Param("id") UUID id);
}
