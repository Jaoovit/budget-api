package com.oliveira.budget.service;

import com.oliveira.budget.domain.address.Address;
import com.oliveira.budget.domain.address.CreateAddressDTO;
import com.oliveira.budget.domain.address.RequestAddressDTO;
import com.oliveira.budget.domain.client.*;
import com.oliveira.budget.domain.user.User;
import com.oliveira.budget.exception.InvalidLengthException;
import com.oliveira.budget.exception.ResourceNotFoundException;
import com.oliveira.budget.repositories.ClientRepository;
import com.oliveira.budget.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ClientService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AddressService addressService;

    public ResponseClientDTO createClient(CreateClientDTO data) {
        Client client = new Client();

        if (data.name().length() > 100) {
            throw new InvalidLengthException("Name is too long. Maximum length is 100");
        }

        client.setName(data.name());
        client.setEmail(data.email());
        client.setPhone(data.phone());

        if (data.userId() == null) {
            throw new InvalidLengthException("User is required");
        }

        User user = userRepository.findUserById(data.userId());

        if (user == null) {
            throw new ResourceNotFoundException("User not found");
        }

        client.setUser(user);

        clientRepository.save(client);

        CreateAddressDTO createAddressDTO = new CreateAddressDTO(data.state(), data.city(), data.street(), data.number(), client);

        addressService.createAddress(createAddressDTO);

        return new ResponseClientDTO(
                client.getName(),
                client.getEmail(),
                client.getPhone(),
                client.getUser().getId());
    }

    public RequestClientDTO getClientById(UUID clientId) {
        Client client = clientRepository.getReferenceById(clientId);

        if (client == null) {
            throw new ResourceNotFoundException("Client not found");
        }

        RequestAddressDTO address = addressService.getAddressByClientId(clientId);

        return new RequestClientDTO(
                client.getId(),
                client.getName(),
                client.getEmail(),
                client.getPhone(),
                address
        );
     }

     public List<RequestClientDTO> getClientsByUserId(int page, int size,UUID userId) {
         Pageable pageable = PageRequest.of(page, size);

        Page<Client> clients = clientRepository.findClientsByUserId(userId, pageable);

        return  clients.map(client -> new RequestClientDTO(
                client.getId(),
                client.getName(),
                client.getEmail(),
                client.getPhone(),
                addressService.getAddressByClientId(client.getId()))
        ).stream().toList();
     }

     public RequestClientDTO updateClient(UUID id, UpdateClientDTO data) {
        Client client = clientRepository.getReferenceById(id);

         if (client == null) {
             throw new ResourceNotFoundException("Client not found");
         }

         client.setName(data.name());
         client.setEmail(data.email());
         client.setPhone(data.phone());

         clientRepository.updateClient(id, client.getName(), client.getEmail(), client.getPhone());

         RequestAddressDTO address = new RequestAddressDTO(data.state(), data.city(), data.street(), data.number());

         addressService.updateAddress(id, address);

         return new RequestClientDTO(client.getId(), client.getName(), client.getEmail(), client.getPhone(), address);
     }

     public void deleteClient(UUID id) {
        Client client = clientRepository.findClientById(id);

        if (client == null) {
            throw new ResourceNotFoundException("Client not found");
        }

        addressService.deleteAddress(client.getAddress().getId());
        clientRepository.deleteById(client.getId());
     }
}
