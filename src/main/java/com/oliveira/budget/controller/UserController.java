package com.oliveira.budget.controller;
import com.oliveira.budget.domain.user.RequestUserDTO;
import com.oliveira.budget.domain.user.User;
import com.oliveira.budget.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<RequestUserDTO>> getUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        List<RequestUserDTO> users = userService.getUsers(page, size);
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RequestUserDTO> getUserById(@PathVariable UUID id) {
        RequestUserDTO user = userService.getUserById(id).getBody();
        return ResponseEntity.ok(user);
    }

}
