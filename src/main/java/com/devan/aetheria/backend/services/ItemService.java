package com.devan.aetheria.backend.services;

import com.devan.aetheria.backend.dto.ItemRequest;
import com.devan.aetheria.backend.dto.ItemView;
import com.devan.aetheria.backend.entities.ItemEntity;
import com.devan.aetheria.backend.repositories.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
    private final ItemRepository repository;

    public ItemService(ItemRepository repository) {
        this.repository = repository;
    }

    public List<ItemView> findAllViews() {
        return repository.findAll()
                .stream()
                .map(ItemView::fromEntity)
                .toList();
    }

    public ItemView findViewById(Long id) {
        return ItemView.fromEntity(findById(id));
    }

    public List<ItemView> findByItemType(Integer itemType) {
        return repository.findByItemType(itemType)
                .stream()
                .map(ItemView::fromEntity)
                .toList();
    }

    public ItemEntity findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Item not found"));
    }

    public ItemEntity create(ItemRequest request) {
        ItemEntity entity = new ItemEntity();
        apply(entity, request);
        return repository.save(entity);
    }

    public ItemEntity update(Long id, ItemRequest request) {
        ItemEntity entity = findById(id);
        apply(entity, request);
        return repository.save(entity);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    private void apply(ItemEntity entity, ItemRequest request) {
        entity.setItemName(request.itemName);
        entity.setItemType(request.itemType);
        entity.setPower(request.power);
    }
}
