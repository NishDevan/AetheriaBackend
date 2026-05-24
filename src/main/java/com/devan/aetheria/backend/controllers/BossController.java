package com.devan.aetheria.backend.controllers;

import com.devan.aetheria.backend.dto.BossRequest;
import com.devan.aetheria.backend.dto.BossView;
import com.devan.aetheria.backend.entities.BossEntity;
import com.devan.aetheria.backend.services.BossService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bosses")
public class BossController {
    private final BossService service;

    public BossController(BossService service) {
        this.service = service;
    }

    @GetMapping
    public List<BossView> getAll() {
        return service.findAllViews();
    }

    @GetMapping("/{id}")
    public BossView getById(@PathVariable Long id) {
        return service.findViewById(id);
    }

    @GetMapping("/name/{name}")
    public List<BossView> searchByName(@PathVariable String name) {
        return service.searchByName(name);
    }

    @PostMapping
    public BossView create(@Valid @RequestBody BossRequest request) {
        BossEntity entity = service.create(request);
        return BossView.fromEntity(entity);
    }

    @PutMapping("/{id}")
    public BossView update(@PathVariable Long id, @Valid @RequestBody BossRequest request) {
        BossEntity entity = service.update(id, request);
        return BossView.fromEntity(entity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
