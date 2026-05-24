package com.devan.aetheria.backend.repositories;

import com.devan.aetheria.backend.entities.BossEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BossRepository extends JpaRepository<BossEntity, Long> {
    @Query("select b from BossEntity b left join fetch b.specialDrop")
    List<BossEntity> findAllWithSpecialDrop();

    @Query("select b from BossEntity b left join fetch b.specialDrop where b.id = :id")
    Optional<BossEntity> findByIdWithSpecialDrop(@Param("id") Long id);

    @Query("select b from BossEntity b left join fetch b.specialDrop where lower(b.name) like lower(concat('%', :name, '%'))")
    List<BossEntity> findByNameLike(@Param("name") String name);
}
