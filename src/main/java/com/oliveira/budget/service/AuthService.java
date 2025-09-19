package com.oliveira.budget.service;

import com.oliveira.budget.domain.auth.TokenDTO;
import com.oliveira.budget.domain.user.AuthUserDTO;
import com.oliveira.budget.domain.user.CreateUserDTO;
import com.oliveira.budget.domain.user.User;
import com.oliveira.budget.exception.InvalidInputException;
import com.oliveira.budget.exception.ResourceNotFoundException;
import com.oliveira.budget.exception.WrongPasswordException;
import com.oliveira.budget.repositories.UserRepository;
import com.oliveira.budget.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    public User register(CreateUserDTO data) {

        if (userRepository.findUser(data.email()) != null) {
            throw new InvalidInputException("Email already registered");
        }

        if (!data.password().equals(data.confirmPassword())) {
            throw new InvalidInputException("Passwords don't match");
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(data.password());

        User user = new User();
        user.setEmail(data.email());
        user.setName(data.name());
        user.setPassword(encodedPassword);

        userRepository.save(user);

        user.setPassword(null);

        return user;
    }

    public TokenDTO login(AuthUserDTO data) {
        User user = userRepository.findUser(data.email());

        if (user == null) {
            throw new ResourceNotFoundException("User not found");
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (!passwordEncoder.matches(data.password(), user.getPassword())) {
            throw new WrongPasswordException("Invalid password");
        }

        String token = jwtUtil.generateToken(user.getEmail());
        return new TokenDTO(token, user.getId());
    }
}
