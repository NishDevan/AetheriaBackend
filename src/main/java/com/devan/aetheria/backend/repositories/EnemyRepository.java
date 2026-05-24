package com.devan.aetheria.backend.repositories;

import com.devan.aetheria.backend.entities.EnemyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EnemyRepository extends JpaRepository<EnemyEntity, Long> {
    @Query("select e from EnemyEntity e left join fetch e.dropItem")
    List<EnemyEntity> findAllWithDropItem();

    @Query("select e from EnemyEntity e left join fetch e.dropItem where e.id = :id")
    Optional<EnemyEntity> findByIdWithDropItem(@Param("id") Long id);

    @Query("select e from EnemyEntity e left join fetch e.dropItem where lower(e.name) like lower(concat('%', :name, '%'))")
    List<EnemyEntity> findByNameLike(@Param("name") String name);
}
