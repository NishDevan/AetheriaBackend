package com.devan.aetheria.backend.services;

import com.devan.aetheria.backend.dto.BossProgressView;
import com.devan.aetheria.backend.dto.InventoryView;
import com.devan.aetheria.backend.dto.PlayerRequest;
import com.devan.aetheria.backend.dto.PlayerSummary;
import com.devan.aetheria.backend.dto.PlayerView;
import com.devan.aetheria.backend.entities.PlayerEntity;
import com.devan.aetheria.backend.repositories.PlayerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PlayerService {
    private final PlayerRepository repository;
    private final InventoryService inventoryService;
    private final BossProgressService bossProgressService;

    public PlayerService(PlayerRepository repository,
                         InventoryService inventoryService,
                         BossProgressService bossProgressService) {
        this.repository = repository;
        this.inventoryService = inventoryService;
        this.bossProgressService = bossProgressService;
    }

    public List<PlayerView> findAllViews() {
        return repository.findAll()
                .stream()
                .map(PlayerView::fromEntity)
                .toList();
    }

    public PlayerView findViewById(Long id) {
        return PlayerView.fromEntity(findById(id));
    }

    public List<PlayerEntity> findAll() {
        return repository.findAll();
    }

    public PlayerEntity findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Player not found"));
    }

    @Transactional(readOnly = true)
    public PlayerSummary getSummary(Long id) {
        PlayerEntity player = findById(id);
        List<InventoryView> inventory = inventoryService.findByPlayerId(id);
        List<BossProgressView> bossProgress = bossProgressService.findByPlayerId(id);
        return PlayerSummary.from(player, inventory, bossProgress);
    }

    public PlayerEntity create(PlayerRequest request) {
        PlayerEntity entity = new PlayerEntity();
        apply(entity, request);
        return repository.save(entity);
    }

    public PlayerEntity update(Long id, PlayerRequest request) {
        PlayerEntity entity = findById(id);
        apply(entity, request);
        return repository.save(entity);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    private void apply(PlayerEntity entity, PlayerRequest request) {
        entity.setUsername(request.username);
        entity.setHealth(request.health);
        entity.setLocX(request.locX);
        entity.setLocY(request.locY);
        entity.setLevel(request.level);
    }
}
