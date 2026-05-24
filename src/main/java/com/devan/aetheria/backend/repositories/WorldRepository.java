package com.devan.aetheria.backend.repositories;

import com.devan.aetheria.backend.entities.WorldEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface WorldRepository extends JpaRepository<WorldEntity, Long> {
    Optional<WorldEntity> findByName(String name);

    @Query("SELECT w FROM WorldEntity w WHERE w.isActive = true ORDER BY w.lastPlayed DESC")
    List<WorldEntity> findAllActive();

    @Query("SELECT w FROM WorldEntity w WHERE w.isActive = true ORDER BY w.createdAt DESC LIMIT 1")
    Optional<WorldEntity> findMostRecent();
}

