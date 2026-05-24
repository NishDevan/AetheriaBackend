package com.devan.aetheria.backend.repositories;

import com.devan.aetheria.backend.entities.InventoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface InventoryRepository extends JpaRepository<InventoryEntity, Long> {
    @Query("select i from InventoryEntity i join fetch i.item join fetch i.player where i.player.id = :playerId order by i.slotIndex")
    List<InventoryEntity> findByPlayerId(@Param("playerId") Long playerId);

    @Query("select i from InventoryEntity i join fetch i.item join fetch i.player order by i.player.id, i.slotIndex")
    List<InventoryEntity> findAllWithItem();

    @Query("select i from InventoryEntity i join fetch i.item join fetch i.player where i.id = :id")
    Optional<InventoryEntity> findByIdWithItem(@Param("id") Long id);
}
