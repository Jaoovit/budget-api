package com.oliveira.budget.controller;

import com.oliveira.budget.domain.client.CreateClientDTO;
import com.oliveira.budget.domain.client.RequestClientDTO;
import com.oliveira.budget.domain.client.ResponseClientDTO;
import com.oliveira.budget.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping
    public ResponseEntity<ResponseClientDTO> createClient(@RequestBody CreateClientDTO data) {
        ResponseClientDTO client = clientService.createClient(data);
        return ResponseEntity.ok(client);
    }

    @GetMapping("{id}")
    public ResponseEntity<RequestClientDTO> getClientById(@PathVariable UUID id) {
        RequestClientDTO client = clientService.getClientById(id);
        return ResponseEntity.ok(client);
    }
}
