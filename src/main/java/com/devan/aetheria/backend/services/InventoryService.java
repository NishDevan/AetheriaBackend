package com.devan.aetheria.backend.services;

import com.devan.aetheria.backend.dto.InventoryRequest;
import com.devan.aetheria.backend.dto.InventoryView;
import com.devan.aetheria.backend.entities.InventoryEntity;
import com.devan.aetheria.backend.entities.ItemEntity;
import com.devan.aetheria.backend.entities.PlayerEntity;
import com.devan.aetheria.backend.repositories.InventoryRepository;
import com.devan.aetheria.backend.repositories.ItemRepository;
import com.devan.aetheria.backend.repositories.PlayerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InventoryService {
    private final InventoryRepository repository;
    private final PlayerRepository playerRepository;
    private final ItemRepository itemRepository;

    public InventoryService(InventoryRepository repository,
                            PlayerRepository playerRepository,
                            ItemRepository itemRepository) {
        this.repository = repository;
        this.playerRepository = playerRepository;
        this.itemRepository = itemRepository;
    }

    public List<InventoryView> findAllViews() {
        return repository.findAllWithItem()
                .stream()
                .map(InventoryView::fromEntity)
                .toList();
    }

    public InventoryView findViewById(Long id) {
        return InventoryView.fromEntity(findEntityWithItem(id));
    }

    public List<InventoryEntity> findAll() {
        return repository.findAll();
    }

    public InventoryEntity findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Inventory not found"));
    }

    @Transactional(readOnly = true)
    public List<InventoryView> findByPlayerId(Long playerId) {
        return repository.findByPlayerId(playerId)
                .stream()
                .map(InventoryView::fromEntity)
                .toList();
    }

    public InventoryEntity create(InventoryRequest request) {
        InventoryEntity entity = new InventoryEntity();
        apply(entity, request);
        return repository.save(entity);
    }

    public InventoryEntity update(Long id, InventoryRequest request) {
        InventoryEntity entity = findById(id);
        apply(entity, request);
        return repository.save(entity);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    private InventoryEntity findEntityWithItem(Long id) {
        return repository.findByIdWithItem(id)
                .orElseThrow(() -> new IllegalArgumentException("Inventory not found"));
    }

    private void apply(InventoryEntity entity, InventoryRequest request) {
        PlayerEntity player = playerRepository.findById(request.playerId)
                .orElseThrow(() -> new IllegalArgumentException("Player not found"));
        ItemEntity item = itemRepository.findById(request.itemId)
                .orElseThrow(() -> new IllegalArgumentException("Item not found"));
        entity.setPlayer(player);
        entity.setItem(item);
        entity.setQuantity(request.quantity);
        entity.setSlotIndex(request.slotIndex);
    }
}
