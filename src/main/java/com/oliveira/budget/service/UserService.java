package com.oliveira.budget.service;

import com.oliveira.budget.domain.user.CreateUserDTO;
import com.oliveira.budget.domain.user.User;
import com.oliveira.budget.repositories.UserRepository;
import jakarta.validation.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(CreateUserDTO createUserDTO) {

        if (userRepository.findUser(createUserDTO.email()) != null) {
            System.out.println("This email already exist");
        }

        if (!createUserDTO.password().equals(createUserDTO.confirmPassword())) {
            System.out.println("Password don't match");
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(createUserDTO.password());

        User user = new User();
        user.setEmail(createUserDTO.email());
        user.setName(createUserDTO.name());
        user.setPassword(encodedPassword);

        return user;
    }
}
