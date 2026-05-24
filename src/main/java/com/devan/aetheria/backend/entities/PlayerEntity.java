package com.devan.aetheria.backend.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "players")
public class PlayerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private Integer health;

    @Column(name = "loc_x", nullable = false)
    private Float locX;

    @Column(name = "loc_y", nullable = false)
    private Float locY;

    @Column(nullable = false)
    private Integer level;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getHealth() {
        return health;
    }

    public void setHealth(Integer health) {
        this.health = health;
    }

    public Float getLocX() {
        return locX;
    }

    public void setLocX(Float locX) {
        this.locX = locX;
    }

    public Float getLocY() {
        return locY;
    }

    public void setLocY(Float locY) {
        this.locY = locY;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}

