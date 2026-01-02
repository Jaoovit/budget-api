package com.oliveira.budget.service;

import com.oliveira.budget.domain.user.RequestUserDTO;
import com.oliveira.budget.domain.user.User;
import com.oliveira.budget.exception.ResourceNotFoundException;
import com.oliveira.budget.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public RequestUserDTO getUserById(UUID id) {
        User user = userRepository.findUserById(id);

        if (user == null) {
            throw new ResourceNotFoundException("User not found");
        }

        return new RequestUserDTO(user.getId(), user.getName(), user.getEmail());
    }
}
