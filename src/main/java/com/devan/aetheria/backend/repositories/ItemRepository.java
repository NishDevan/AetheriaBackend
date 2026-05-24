package com.devan.aetheria.backend.repositories;

import com.devan.aetheria.backend.entities.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<ItemEntity, Long> {
    List<ItemEntity> findByItemType(Integer itemType);
}
