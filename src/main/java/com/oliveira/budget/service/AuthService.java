package com.oliveira.budget.service;

import com.oliveira.budget.domain.user.CreateUserDTO;
import com.oliveira.budget.domain.user.User;
import com.oliveira.budget.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    public User register(CreateUserDTO data) {

        if (userRepository.findUser(data.email()) != null) {
            // return a friendly message
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already exists");

        }

        if (!data.password().equals(data.confirmPassword())) {
            // return a friendly message
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Passwords do not match");
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(data.password());

        User user = new User();
        user.setEmail(data.email());
        user.setName(data.name());
        user.setPassword(encodedPassword);

        userRepository.save(user);

        // don't return password
        user.setPassword(null);

        return user;
    }
}
