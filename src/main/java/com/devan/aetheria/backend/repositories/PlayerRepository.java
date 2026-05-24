package com.devan.aetheria.backend.repositories;

import com.devan.aetheria.backend.entities.PlayerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<PlayerEntity, Long> {
}

