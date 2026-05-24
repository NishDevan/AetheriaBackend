package com.devan.aetheria.backend.controllers;

import com.devan.aetheria.backend.dto.PlayerRequest;
import com.devan.aetheria.backend.dto.PlayerSummary;
import com.devan.aetheria.backend.dto.PlayerView;
import com.devan.aetheria.backend.entities.PlayerEntity;
import com.devan.aetheria.backend.services.PlayerService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/players")
public class PlayerController {
    private final PlayerService service;

    public PlayerController(PlayerService service) {
        this.service = service;
    }

    @GetMapping
    public List<PlayerView> getAll() {
        return service.findAllViews();
    }

    @GetMapping("/{id}")
    public PlayerView getById(@PathVariable Long id) {
        return service.findViewById(id);
    }

    @GetMapping("/{id}/summary")
    public PlayerSummary getSummary(@PathVariable Long id) {
        return service.getSummary(id);
    }

    @PostMapping
    public PlayerView create(@Valid @RequestBody PlayerRequest request) {
        PlayerEntity entity = service.create(request);
        return PlayerView.fromEntity(entity);
    }

    @PutMapping("/{id}")
    public PlayerView update(@PathVariable Long id, @Valid @RequestBody PlayerRequest request) {
        PlayerEntity entity = service.update(id, request);
        return PlayerView.fromEntity(entity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
