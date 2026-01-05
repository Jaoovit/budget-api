package com.oliveira.budget.controller;

import com.oliveira.budget.domain.auth.TokenDTO;
import com.oliveira.budget.domain.user.AuthUserDTO;
import com.oliveira.budget.domain.user.RequestUserDTO;
import com.oliveira.budget.domain.user.ResponseUserDTO;
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
    public ResponseEntity<ResponseUserDTO> register(@RequestBody RequestUserDTO data) {
        ResponseUserDTO userDTO = authService.register(data);
        return ResponseEntity.ok(userDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDTO> login(@RequestBody AuthUserDTO data) {
        TokenDTO tokenDTO = authService.login(data);
        return ResponseEntity.ok(tokenDTO);
    }
}
