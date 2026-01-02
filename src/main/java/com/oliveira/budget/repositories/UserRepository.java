package com.oliveira.budget.repositories;

import com.oliveira.budget.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    User findUserById(@Param("id") UUID id);

    User findUserByEmail(@Param("email") String email);
}
