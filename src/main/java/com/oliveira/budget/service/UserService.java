package com.oliveira.budget.service;

import com.oliveira.budget.domain.user.User;
import com.oliveira.budget.repositories.UserRepository;
import jakarta.validation.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

}
