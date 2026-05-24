package com.devan.aetheria.backend.controllers;

import com.devan.aetheria.backend.dto.BossProgressRequest;
import com.devan.aetheria.backend.dto.BossProgressUpdateRequest;
import com.devan.aetheria.backend.dto.BossProgressView;
import com.devan.aetheria.backend.entities.BossProgressEntity;
import com.devan.aetheria.backend.services.BossProgressService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/boss-progress")
public class BossProgressController {
    private final BossProgressService service;

    public BossProgressController(BossProgressService service) {
        this.service = service;
    }

    @GetMapping
    public List<BossProgressView> getAll() {
        return service.findAllViews();
    }

    @GetMapping("/{id}")
    public BossProgressView getById(@PathVariable Long id) {
        return service.findViewById(id);
    }

    @PostMapping
    public BossProgressView create(@Valid @RequestBody BossProgressRequest request) {
        BossProgressEntity entity = service.create(request);
        return BossProgressView.fromEntity(entity);
    }

    @PutMapping("/{id}")
    public BossProgressView update(@PathVariable Long id, @Valid @RequestBody BossProgressRequest request) {
        BossProgressEntity entity = service.update(id, request);
        return BossProgressView.fromEntity(entity);
    }

    @PutMapping("/player/{playerId}/boss/{bossId}")
    public BossProgressView updateByPlayerBoss(@PathVariable Long playerId,
                                               @PathVariable Long bossId,
                                               @Valid @RequestBody BossProgressUpdateRequest request) {
        BossProgressEntity entity = service.upsertByPlayerBoss(playerId, bossId, request.isDefeated);
        return BossProgressView.fromEntity(entity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
