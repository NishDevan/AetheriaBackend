package com.devan.aetheria.backend.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "boss_progress")
public class BossProgressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id", nullable = false)
    private PlayerEntity player;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "boss_id", nullable = false)
    private BossEntity boss;

    @Column(name = "is_defeated", nullable = false)
    private Boolean isDefeated;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PlayerEntity getPlayer() {
        return player;
    }

    public void setPlayer(PlayerEntity player) {
        this.player = player;
    }

    public BossEntity getBoss() {
        return boss;
    }

    public void setBoss(BossEntity boss) {
        this.boss = boss;
    }

    public Boolean getIsDefeated() {
        return isDefeated;
    }

    public void setIsDefeated(Boolean defeated) {
        isDefeated = defeated;
    }
}

