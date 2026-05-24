package com.devan.aetheria.backend.repositories;

import com.devan.aetheria.backend.entities.BossProgressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BossProgressRepository extends JpaRepository<BossProgressEntity, Long> {
    @Query("select bp from BossProgressEntity bp join fetch bp.boss join fetch bp.player where bp.player.id = :playerId")
    List<BossProgressEntity> findByPlayerId(@Param("playerId") Long playerId);

    @Query("select bp from BossProgressEntity bp join fetch bp.boss join fetch bp.player")
    List<BossProgressEntity> findAllWithBoss();

    @Query("select bp from BossProgressEntity bp join fetch bp.boss join fetch bp.player where bp.id = :id")
    Optional<BossProgressEntity> findByIdWithBoss(@Param("id") Long id);

    Optional<BossProgressEntity> findByPlayerIdAndBossId(Long playerId, Long bossId);
}
