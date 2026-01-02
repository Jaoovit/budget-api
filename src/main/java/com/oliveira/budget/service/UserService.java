package com.oliveira.budget.service;

import com.oliveira.budget.domain.user.ResponseUserDTO;
import com.oliveira.budget.domain.user.User;
import com.oliveira.budget.exception.ResourceNotFoundException;
import com.oliveira.budget.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public ResponseUserDTO getUserById(UUID id) {
        User user = userRepository.findUserById(id);

        if (user == null) {
            throw new ResourceNotFoundException("User not found");
        }

        return new ResponseUserDTO(user.getId(), user.getName(), user.getEmail());
    }
}
