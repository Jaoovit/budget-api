package com.oliveira.budget.controller;

import com.oliveira.budget.domain.client.RequestClientDTO;
import com.oliveira.budget.domain.client.ResponseClientDTO;
import com.oliveira.budget.domain.client.UpdateClientDTO;
import com.oliveira.budget.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping
    public ResponseEntity<RequestClientDTO> createClient(@RequestBody RequestClientDTO data) {
        RequestClientDTO client = clientService.createClient(data);
        return ResponseEntity.ok(client);
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseClientDTO> getClientById(@PathVariable UUID id) {
        ResponseClientDTO client = clientService.getClientById(id);
        return ResponseEntity.ok(client);
    }

    @GetMapping("user/{userId}")
    public ResponseEntity<List<ResponseClientDTO>> getClientsByUserId(
            @PathVariable UUID userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        List<ResponseClientDTO> clients = clientService.getClientsByUserId(page, size, userId);
        return ResponseEntity.ok(clients);
    }

    @PutMapping("{id}")
    public ResponseEntity<ResponseClientDTO> updateClientById(@PathVariable UUID id, @RequestBody UpdateClientDTO data) {
        ResponseClientDTO client = clientService.updateClient(id, data);
        return ResponseEntity.ok(client);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable UUID id) {
        clientService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }
}
