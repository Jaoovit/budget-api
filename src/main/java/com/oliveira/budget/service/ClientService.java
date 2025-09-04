package com.oliveira.budget.service;

import com.oliveira.budget.domain.address.Address;
import com.oliveira.budget.domain.client.Client;
import com.oliveira.budget.domain.client.CreateClientDTO;
import com.oliveira.budget.domain.client.ResponseClientDTO;
import com.oliveira.budget.domain.user.User;
import com.oliveira.budget.repositories.ClientRepository;
import com.oliveira.budget.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ClientRepository clientRepository;

    public ResponseClientDTO createClient(CreateClientDTO data) {
        Client client = new Client();

        if (data.name().length() > 100) {
            throw new IllegalArgumentException("Name is too long. Maximum length is 100");
        }

        client.setName(data.name());
        client.setEmail(data.email());
        client.setPhone(data.phone());

        if (data.userId() == null) {
            throw new IllegalArgumentException("User is required");
        }

        User user = userRepository.findUserById(data.userId());

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        client.setUser(user);

        clientRepository.save(client);

        //Address address = new Address();

        //address.setState(data.state());
        //address.setCity(data.city());
        //address.setStreet(data.street());
        //address.setNumber(data.number());
        //address.setClient(client);
        //addressRepository.save(address);



        return new ResponseClientDTO(
                client.getName(),
                client.getEmail(),
                client.getPhone(),
                client.getUser().getId());
    }
}
