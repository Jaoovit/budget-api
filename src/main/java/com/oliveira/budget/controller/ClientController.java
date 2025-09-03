package com.oliveira.budget.controller;

import com.oliveira.budget.domain.client.CreateClientDTO;
import com.oliveira.budget.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping()
    public ResponseEntity<CreateClientDTO> createClient(@RequestBody CreateClientDTO data) {
        CreateClientDTO client = clientService.createClient(data);
        return ResponseEntity.ok(client);
    }
}
