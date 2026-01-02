package com.oliveira.budget.controller;

import com.oliveira.budget.domain.user.ResponseUserDTO;
import com.oliveira.budget.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<ResponseUserDTO> getUserById(@PathVariable UUID id) {
        ResponseUserDTO user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }
}
