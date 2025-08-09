package com.oliveira.budget.service;

import com.oliveira.budget.domain.auth.TokenDTO;
import com.oliveira.budget.domain.user.AuthUserDTO;
import com.oliveira.budget.domain.user.CreateUserDTO;
import com.oliveira.budget.domain.user.User;
import com.oliveira.budget.repositories.UserRepository;
import com.oliveira.budget.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    public ResponseEntity register(CreateUserDTO data) {

        if (userRepository.findUser(data.email()) != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email already registered");
        }

        if (!data.password().equals(data.confirmPassword())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Password don't match");
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(data.password());

        User user = new User();
        user.setEmail(data.email());
        user.setName(data.name());
        user.setPassword(encodedPassword);

        userRepository.save(user);

        user.setPassword(null);

        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    public ResponseEntity login(AuthUserDTO data) {
        User user = userRepository.findUser(data.email());

        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        if (!passwordEncoder.matches(data.password(), user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid password");
        }

        String token = jwtUtil.generateToken(user.getEmail());

        TokenDTO auth = new TokenDTO(token);

        return ResponseEntity.ok(auth);
    }
}
