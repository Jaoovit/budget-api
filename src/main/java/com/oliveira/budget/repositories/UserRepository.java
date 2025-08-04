package com.oliveira.budget.repositories;

import com.oliveira.budget.domain.user.User;
import jakarta.validation.constraints.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    @Query("SELECT e FROM User e WHERE e.email = :email")
    public User findUser(@Param("email") String email);
}
