package com.oliveira.budget.service;

import com.oliveira.budget.domain.address.Address;
import com.oliveira.budget.domain.address.CreateAddressDTO;
import com.oliveira.budget.domain.address.RequestAddressDTO;
import com.oliveira.budget.domain.client.Client;
import com.oliveira.budget.exception.ResourceNotFoundException;
import com.oliveira.budget.repositories.AddressRepository;
import com.oliveira.budget.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ClientRepository clientRepository;

    public Address createAddress(CreateAddressDTO data) {
        Address address = new Address();

        address.setState(data.state());
        address.setCity(data.city());
        address.setStreet(data.street());
        address.setNumber(data.number());

        Client client = clientRepository.findById(data.client().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Client not found"));

        address.setClient(client);

        addressRepository.save(address);

        return address;
    }

    public RequestAddressDTO getAddressByClientId(UUID clientId) {
        Address address = addressRepository.findAddressByClientId(clientId);

        if (address == null) {
            throw new ResourceNotFoundException("Address not found");
        }

        return new RequestAddressDTO(
                address.getState(),
                address.getCity(),
                address.getStreet(),
                address.getNumber()
        );
    }

    public RequestAddressDTO updateAddress(UUID id, RequestAddressDTO data) {
        Address address = addressRepository.findAddressByClientId(id);

        if (address == null) {
            throw new ResourceNotFoundException("Address not found");
        }

        address.setState(data.state());
        address.setCity(data.city());
        address.setStreet(data.street());
        address.setNumber(data.number());

        addressRepository.updateAddress(id, data.state(), data.city(), data.street(), data.number());

        return new RequestAddressDTO(address.getState(), address.getCity(), address.getStreet(), address.getNumber());
    }

    public void deleteAddress(UUID id) {
        Address address = addressRepository.findAddressById(id);

        if (address == null) {
            throw new ResourceNotFoundException("Address not found");
        }

        addressRepository.deleteById(address.getId());
    }

}
