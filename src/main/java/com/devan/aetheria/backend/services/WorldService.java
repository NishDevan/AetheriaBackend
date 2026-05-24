package com.devan.aetheria.backend.services;

import com.devan.aetheria.backend.dto.WorldRequest;
import com.devan.aetheria.backend.dto.WorldView;
import com.devan.aetheria.backend.entities.WorldEntity;
import com.devan.aetheria.backend.repositories.WorldRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class WorldService {
    private final WorldRepository repository;

    public WorldService(WorldRepository repository) {
        this.repository = repository;
    }

    public List<WorldView> findAllActive() {
        return repository.findAllActive()
                .stream()
                .map(WorldView::fromEntity)
                .toList();
    }

    public WorldView findViewById(Long id) {
        return WorldView.fromEntity(findById(id));
    }

    public WorldEntity findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("World not found with ID: " + id));
    }

    public WorldView create(WorldRequest request) {
        // Check if world with same name already exists
        if (repository.findByName(request.name).isPresent()) {
            throw new IllegalArgumentException("World with name '" + request.name + "' already exists");
        }

        WorldEntity entity = new WorldEntity();
        entity.setName(request.name);
        entity.setDifficulty(request.difficulty != null ? request.difficulty : "NORMAL");

        // Generate seed if not provided
        Long seed = request.seed != null ? request.seed : new Random().nextLong();
        entity.setSeed(seed);

        // Set biome type or random if not provided
        String biomeType = request.biomeType != null ? request.biomeType : getRandomBiome();
        entity.setBiomeType(biomeType);

        entity.setWidth(request.width != null ? request.width : 120);
        entity.setHeight(request.height != null ? request.height : 60);
        entity.setGameTime(0L);
        entity.setCreatedAt(new Date());
        entity.setLastPlayed(new Date());
        entity.setIsActive(true);

        WorldEntity saved = repository.save(entity);
        return WorldView.fromEntity(saved);
    }

    public WorldView update(Long id, WorldRequest request) {
        WorldEntity entity = findById(id);

        // Update only provided fields
        if (request.name != null && !request.name.isBlank()) {
            entity.setName(request.name);
        }
        if (request.difficulty != null) {
            entity.setDifficulty(request.difficulty);
        }
        if (request.biomeType != null) {
            entity.setBiomeType(request.biomeType);
        }

        WorldEntity saved = repository.save(entity);
        return WorldView.fromEntity(saved);
    }

    @Transactional
    public WorldView updateGameTime(Long id, Long elapsedTime) {
        WorldEntity entity = findById(id);
        entity.setGameTime(entity.getGameTime() + elapsedTime);
        entity.setLastPlayed(new Date());
        WorldEntity saved = repository.save(entity);
        return WorldView.fromEntity(saved);
    }

    public void delete(Long id) {
        WorldEntity entity = findById(id);
        entity.setIsActive(false);
        repository.save(entity);
    }

    @Transactional
    public void hardDelete(Long id) {
        repository.deleteById(id);
    }

    private String getRandomBiome() {
        String[] biomes = {"FOREST", "WINTER"};
        return biomes[new Random().nextInt(biomes.length)];
    }
}

