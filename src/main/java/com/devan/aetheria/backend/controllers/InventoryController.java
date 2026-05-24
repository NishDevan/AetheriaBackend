package com.devan.aetheria.backend.controllers;

import com.devan.aetheria.backend.dto.InventoryRequest;
import com.devan.aetheria.backend.dto.InventoryView;
import com.devan.aetheria.backend.entities.InventoryEntity;
import com.devan.aetheria.backend.services.InventoryService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventories")
public class InventoryController {
    private final InventoryService service;

    public InventoryController(InventoryService service) {
        this.service = service;
    }

    @GetMapping
    public List<InventoryView> getAll() {
        return service.findAllViews();
    }

    @GetMapping("/{id}")
    public InventoryView getById(@PathVariable Long id) {
        return service.findViewById(id);
    }

    @GetMapping("/player/{playerId}")
    public List<InventoryView> getByPlayer(@PathVariable Long playerId) {
        return service.findByPlayerId(playerId);
    }

    @PostMapping
    public InventoryView create(@Valid @RequestBody InventoryRequest request) {
        InventoryEntity entity = service.create(request);
        return InventoryView.fromEntity(entity);
    }

    @PutMapping("/{id}")
    public InventoryView update(@PathVariable Long id, @Valid @RequestBody InventoryRequest request) {
        InventoryEntity entity = service.update(id, request);
        return InventoryView.fromEntity(entity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
