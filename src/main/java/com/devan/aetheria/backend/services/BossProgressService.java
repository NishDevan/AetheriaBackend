package com.devan.aetheria.backend.services;

import com.devan.aetheria.backend.dto.BossProgressRequest;
import com.devan.aetheria.backend.dto.BossProgressView;
import com.devan.aetheria.backend.entities.BossEntity;
import com.devan.aetheria.backend.entities.BossProgressEntity;
import com.devan.aetheria.backend.entities.PlayerEntity;
import com.devan.aetheria.backend.repositories.BossProgressRepository;
import com.devan.aetheria.backend.repositories.BossRepository;
import com.devan.aetheria.backend.repositories.PlayerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BossProgressService {
    private final BossProgressRepository repository;
    private final PlayerRepository playerRepository;
    private final BossRepository bossRepository;

    public BossProgressService(BossProgressRepository repository,
                               PlayerRepository playerRepository,
                               BossRepository bossRepository) {
        this.repository = repository;
        this.playerRepository = playerRepository;
        this.bossRepository = bossRepository;
    }

    public List<BossProgressView> findAllViews() {
        return repository.findAllWithBoss()
                .stream()
                .map(BossProgressView::fromEntity)
                .toList();
    }

    public BossProgressView findViewById(Long id) {
        return BossProgressView.fromEntity(findEntityWithBoss(id));
    }

    public List<BossProgressEntity> findAll() {
        return repository.findAll();
    }

    public BossProgressEntity findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Boss progress not found"));
    }

    @Transactional(readOnly = true)
    public List<BossProgressView> findByPlayerId(Long playerId) {
        return repository.findByPlayerId(playerId)
                .stream()
                .map(BossProgressView::fromEntity)
                .toList();
    }

    public BossProgressEntity create(BossProgressRequest request) {
        BossProgressEntity entity = new BossProgressEntity();
        apply(entity, request);
        return repository.save(entity);
    }

    public BossProgressEntity update(Long id, BossProgressRequest request) {
        BossProgressEntity entity = findById(id);
        apply(entity, request);
        return repository.save(entity);
    }

    public BossProgressEntity upsertByPlayerBoss(Long playerId, Long bossId, Boolean isDefeated) {
        BossProgressEntity entity = repository.findByPlayerIdAndBossId(playerId, bossId)
                .orElseGet(BossProgressEntity::new);

        PlayerEntity player = playerRepository.findById(playerId)
                .orElseThrow(() -> new IllegalArgumentException("Player not found"));
        BossEntity boss = bossRepository.findById(bossId)
                .orElseThrow(() -> new IllegalArgumentException("Boss not found"));

        entity.setPlayer(player);
        entity.setBoss(boss);
        entity.setIsDefeated(Boolean.TRUE.equals(isDefeated));
        return repository.save(entity);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    private BossProgressEntity findEntityWithBoss(Long id) {
        return repository.findByIdWithBoss(id)
                .orElseThrow(() -> new IllegalArgumentException("Boss progress not found"));
    }

    private void apply(BossProgressEntity entity, BossProgressRequest request) {
        PlayerEntity player = playerRepository.findById(request.playerId)
                .orElseThrow(() -> new IllegalArgumentException("Player not found"));
        BossEntity boss = bossRepository.findById(request.bossId)
                .orElseThrow(() -> new IllegalArgumentException("Boss not found"));
        entity.setPlayer(player);
        entity.setBoss(boss);
        entity.setIsDefeated(request.isDefeated);
    }
}
