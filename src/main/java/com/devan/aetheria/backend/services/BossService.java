package com.devan.aetheria.backend.services;

import com.devan.aetheria.backend.dto.BossRequest;
import com.devan.aetheria.backend.dto.BossView;
import com.devan.aetheria.backend.entities.BossEntity;
import com.devan.aetheria.backend.entities.ItemEntity;
import com.devan.aetheria.backend.repositories.BossRepository;
import com.devan.aetheria.backend.repositories.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BossService {
    private final BossRepository repository;
    private final ItemRepository itemRepository;

    public BossService(BossRepository repository, ItemRepository itemRepository) {
        this.repository = repository;
        this.itemRepository = itemRepository;
    }

    public List<BossView> findAllViews() {
        return repository.findAllWithSpecialDrop()
                .stream()
                .map(BossView::fromEntity)
                .toList();
    }

    public BossView findViewById(Long id) {
        return BossView.fromEntity(findEntityWithDrop(id));
    }

    public List<BossView> searchByName(String name) {
        return repository.findByNameLike(name)
                .stream()
                .map(BossView::fromEntity)
                .toList();
    }

    public BossEntity findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Boss not found"));
    }

    public BossEntity create(BossRequest request) {
        BossEntity entity = new BossEntity();
        apply(entity, request);
        return repository.save(entity);
    }

    public BossEntity update(Long id, BossRequest request) {
        BossEntity entity = findById(id);
        apply(entity, request);
        return repository.save(entity);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    private BossEntity findEntityWithDrop(Long id) {
        return repository.findByIdWithSpecialDrop(id)
                .orElseThrow(() -> new IllegalArgumentException("Boss not found"));
    }

    private void apply(BossEntity entity, BossRequest request) {
        entity.setName(request.name);
        entity.setMaxHealth(request.maxHealth);
        entity.setBaseDamage(request.baseDamage);
        if (request.specialDropId != null) {
            ItemEntity item = itemRepository.findById(request.specialDropId)
                    .orElseThrow(() -> new IllegalArgumentException("Item not found"));
            entity.setSpecialDrop(item);
        } else {
            entity.setSpecialDrop(null);
        }
    }
}
