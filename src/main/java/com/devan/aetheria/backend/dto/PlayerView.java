package com.devan.aetheria.backend.dto;

import com.devan.aetheria.backend.entities.PlayerEntity;

public class PlayerView {
    private Long id;
    private String username;
    private Integer health;
    private Float locX;
    private Float locY;
    private Integer level;

    public static PlayerView fromEntity(PlayerEntity entity) {
        PlayerView view = new PlayerView();
        view.id = entity.getId();
        view.username = entity.getUsername();
        view.health = entity.getHealth();
        view.locX = entity.getLocX();
        view.locY = entity.getLocY();
        view.level = entity.getLevel();
        return view;
    }

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

