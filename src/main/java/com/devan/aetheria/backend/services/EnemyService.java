package com.devan.aetheria.backend.services;

import com.devan.aetheria.backend.dto.EnemyRequest;
import com.devan.aetheria.backend.dto.EnemyView;
import com.devan.aetheria.backend.entities.EnemyEntity;
import com.devan.aetheria.backend.entities.ItemEntity;
import com.devan.aetheria.backend.repositories.EnemyRepository;
import com.devan.aetheria.backend.repositories.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnemyService {
    private final EnemyRepository repository;
    private final ItemRepository itemRepository;

    public EnemyService(EnemyRepository repository, ItemRepository itemRepository) {
        this.repository = repository;
        this.itemRepository = itemRepository;
    }

    public List<EnemyView> findAllViews() {
        return repository.findAllWithDropItem()
                .stream()
                .map(EnemyView::fromEntity)
                .toList();
    }

    public EnemyView findViewById(Long id) {
        return EnemyView.fromEntity(findEntityWithDrop(id));
    }

    public List<EnemyView> searchByName(String name) {
        return repository.findByNameLike(name)
                .stream()
                .map(EnemyView::fromEntity)
                .toList();
    }

    public EnemyEntity findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Enemy not found"));
    }

    public EnemyEntity create(EnemyRequest request) {
        EnemyEntity entity = new EnemyEntity();
        apply(entity, request);
        return repository.save(entity);
    }

    public EnemyEntity update(Long id, EnemyRequest request) {
        EnemyEntity entity = findById(id);
        apply(entity, request);
        return repository.save(entity);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    private EnemyEntity findEntityWithDrop(Long id) {
        return repository.findByIdWithDropItem(id)
                .orElseThrow(() -> new IllegalArgumentException("Enemy not found"));
    }

    private void apply(EnemyEntity entity, EnemyRequest request) {
        entity.setName(request.name);
        entity.setMaxHealth(request.maxHealth);
        entity.setBaseDamage(request.baseDamage);
        if (request.dropItemId != null) {
            ItemEntity item = itemRepository.findById(request.dropItemId)
                    .orElseThrow(() -> new IllegalArgumentException("Item not found"));
            entity.setDropItem(item);
        } else {
            entity.setDropItem(null);
        }
    }
}
