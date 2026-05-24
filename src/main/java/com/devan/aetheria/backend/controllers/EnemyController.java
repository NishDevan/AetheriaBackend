package com.devan.aetheria.backend.controllers;

import com.devan.aetheria.backend.dto.EnemyRequest;
import com.devan.aetheria.backend.dto.EnemyView;
import com.devan.aetheria.backend.entities.EnemyEntity;
import com.devan.aetheria.backend.services.EnemyService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enemies")
public class EnemyController {
    private final EnemyService service;

    public EnemyController(EnemyService service) {
        this.service = service;
    }

    @GetMapping
    public List<EnemyView> getAll() {
        return service.findAllViews();
    }

    @GetMapping("/{id}")
    public EnemyView getById(@PathVariable Long id) {
        return service.findViewById(id);
    }

    @GetMapping("/name/{name}")
    public List<EnemyView> searchByName(@PathVariable String name) {
        return service.searchByName(name);
    }

    @PostMapping
    public EnemyView create(@Valid @RequestBody EnemyRequest request) {
        EnemyEntity entity = service.create(request);
        return EnemyView.fromEntity(entity);
    }

    @PutMapping("/{id}")
    public EnemyView update(@PathVariable Long id, @Valid @RequestBody EnemyRequest request) {
        EnemyEntity entity = service.update(id, request);
        return EnemyView.fromEntity(entity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
