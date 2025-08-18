package com.oliveira.budget.repositories;

import com.oliveira.budget.domain.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    @Query("SELECT e FROM User e WHERE e.email = :email")
    public User findUser(@Param("email") String email);

    @Query("SELECT e FROM User e")
    public Page<User> findAllUsers(Pageable pageable);

    @Query("SELECT e FROM User e WHERE e.id = :id")
    public User findUserById(@Param("id") UUID id);
}
