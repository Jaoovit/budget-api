package com.oliveira.budget.controller;

import com.oliveira.budget.domain.item.CreateItemDTO;
import com.oliveira.budget.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    public ResponseEntity<CreateItemDTO> createItem(CreateItemDTO data) {
        CreateItemDTO item = itemService.createItem(data);
        return ResponseEntity.ok(item);
    }

}
