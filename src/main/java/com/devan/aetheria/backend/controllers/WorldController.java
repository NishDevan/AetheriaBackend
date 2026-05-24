package com.devan.aetheria.backend.controllers;

import com.devan.aetheria.backend.dto.WorldRequest;
import com.devan.aetheria.backend.dto.WorldView;
import com.devan.aetheria.backend.services.WorldService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/worlds")
@CrossOrigin(origins = "*")
public class WorldController {
    private final WorldService service;

    public WorldController(WorldService service) {
        this.service = service;
    }

    /**
     * Get all active worlds sorted by last played
     */
    @GetMapping
    public List<WorldView> getAll() {
        return service.findAllActive();
    }

    /**
     * Get specific world by ID
     */
    @GetMapping("/{id}")
    public WorldView getById(@PathVariable Long id) {
        return service.findViewById(id);
    }

    /**
     * Create a new world
     */
    @PostMapping
    public WorldView create(@Valid @RequestBody WorldRequest request) {
        return service.create(request);
    }

    /**
     * Update world properties (name, difficulty, biome)
     */
    @PutMapping("/{id}")
    public WorldView update(@PathVariable Long id, @Valid @RequestBody WorldRequest request) {
        return service.update(id, request);
    }

    /**
     * Update game time for a world (call when saving progress)
     */
    @PutMapping("/{id}/gametime")
    public WorldView updateGameTime(@PathVariable Long id, @RequestParam Long elapsedMs) {
        return service.updateGameTime(id, elapsedMs);
    }

    /**
     * Soft delete - marks world as inactive but keeps data
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Hard delete - permanently removes world from database
     */
    @DeleteMapping("/{id}/permanent")
    public ResponseEntity<Void> hardDelete(@PathVariable Long id) {
        service.hardDelete(id);
        return ResponseEntity.noContent().build();
    }
}

