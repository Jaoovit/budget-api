package com.oliveira.budget.controller;

import com.oliveira.budget.domain.item.ChangeQuantityDTO;
import com.oliveira.budget.domain.item.RequestItemDTO;
import com.oliveira.budget.domain.item.ResponseItemDTO;
import com.oliveira.budget.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping
    public ResponseEntity<RequestItemDTO> createItem(@RequestBody RequestItemDTO data) {
        RequestItemDTO item = itemService.createItem(data);
        return ResponseEntity.ok(item);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ResponseItemDTO> updateItemQuantity(@PathVariable UUID id, @RequestBody ChangeQuantityDTO data) {
        ResponseItemDTO item = itemService.updateItemQuantity(id, data);
        return ResponseEntity.ok(item);
    }

}
