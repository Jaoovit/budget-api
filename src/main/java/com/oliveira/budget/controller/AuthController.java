package com.oliveira.budget.controller;

import com.oliveira.budget.domain.user.CreateUserDTO;
import com.oliveira.budget.domain.user.User;
import com.oliveira.budget.service.AuthService;
import com.oliveira.budget.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody CreateUserDTO data) {
        User user = this.authService.createUser(data);
        return ResponseEntity.ok(user);
    }
}
