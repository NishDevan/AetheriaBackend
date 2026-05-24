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
@Table(name = "bosses")
public class BossEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "max_health", nullable = false)
    private Integer maxHealth;

    @Column(name = "base_damage", nullable = false)
    private Integer baseDamage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "special_drop_id")
    private ItemEntity specialDrop;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(Integer maxHealth) {
        this.maxHealth = maxHealth;
    }

    public Integer getBaseDamage() {
        return baseDamage;
    }

    public void setBaseDamage(Integer baseDamage) {
        this.baseDamage = baseDamage;
    }

    public ItemEntity getSpecialDrop() {
        return specialDrop;
    }

    public void setSpecialDrop(ItemEntity specialDrop) {
        this.specialDrop = specialDrop;
    }
}

