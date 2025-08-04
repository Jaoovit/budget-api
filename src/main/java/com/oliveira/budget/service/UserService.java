package com.oliveira.budget.service;

import com.oliveira.budget.domain.user.CreateUserDTO;
import com.oliveira.budget.domain.user.User;
import com.oliveira.budget.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(CreateUserDTO data) {

        if (userRepository.findUser(data.email()) != null) {
            System.out.println("This email already exist");
        }

        if (!data.password().equals(data.confirmPassword())) {
            System.out.println("Password don't match");
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(data.password());

        User user = new User();
        user.setEmail(data.email());
        user.setName(data.name());
        user.setPassword(encodedPassword);

        return user;
    }
}
