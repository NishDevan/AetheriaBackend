package com.devan.aetheria.backend.controllers;

import com.devan.aetheria.backend.dto.ItemRequest;
import com.devan.aetheria.backend.dto.ItemView;
import com.devan.aetheria.backend.entities.ItemEntity;
import com.devan.aetheria.backend.services.ItemService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items")
public class ItemController {
    private final ItemService service;

    public ItemController(ItemService service) {
        this.service = service;
    }

    @GetMapping
    public List<ItemView> getAll() {
        return service.findAllViews();
    }

    @GetMapping("/{id}")
    public ItemView getById(@PathVariable Long id) {
        return service.findViewById(id);
    }

    @GetMapping("/type/{itemType}")
    public List<ItemView> getByType(@PathVariable Integer itemType) {
        return service.findByItemType(itemType);
    }

    @PostMapping
    public ItemView create(@Valid @RequestBody ItemRequest request) {
        ItemEntity entity = service.create(request);
        return ItemView.fromEntity(entity);
    }

    @PutMapping("/{id}")
    public ItemView update(@PathVariable Long id, @Valid @RequestBody ItemRequest request) {
        ItemEntity entity = service.update(id, request);
        return ItemView.fromEntity(entity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
