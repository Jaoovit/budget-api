package com.oliveira.budget.service;

import com.oliveira.budget.domain.user.RequestUserDTO;
import com.oliveira.budget.domain.user.User;
import com.oliveira.budget.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<RequestUserDTO> getUsers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<User> userPage = userRepository.findAllUsers(pageable);

        return userPage.map(user -> new RequestUserDTO(
                user.getName(),
                user.getEmail()
        )).stream().toList();
    }
}
