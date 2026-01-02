package com.oliveira.budget.controller;

import com.oliveira.budget.domain.auth.TokenDTO;
import com.oliveira.budget.domain.user.AuthUserDTO;
import com.oliveira.budget.domain.user.RequestUserDTO;
import com.oliveira.budget.domain.user.User;
import com.oliveira.budget.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RequestUserDTO data) {
        User user = authService.register(data);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthUserDTO data) {
        TokenDTO tokenDTO = authService.login(data);
        return ResponseEntity.ok(tokenDTO);
    }
}
