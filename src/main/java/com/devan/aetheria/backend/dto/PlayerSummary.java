package com.devan.aetheria.backend.dto;

import com.devan.aetheria.backend.entities.PlayerEntity;

import java.util.List;

public class PlayerSummary {
    private Long id;
    private String username;
    private Integer health;
    private Float locX;
    private Float locY;
    private Integer level;
    private List<InventoryView> inventory;
    private List<BossProgressView> bossProgress;

    public static PlayerSummary from(PlayerEntity player,
                                     List<InventoryView> inventory,
                                     List<BossProgressView> bossProgress) {
        PlayerSummary summary = new PlayerSummary();
        summary.id = player.getId();
        summary.username = player.getUsername();
        summary.health = player.getHealth();
        summary.locX = player.getLocX();
        summary.locY = player.getLocY();
        summary.level = player.getLevel();
        summary.inventory = inventory;
        summary.bossProgress = bossProgress;
        return summary;
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

    public List<InventoryView> getInventory() {
        return inventory;
    }

    public void setInventory(List<InventoryView> inventory) {
        this.inventory = inventory;
    }

    public List<BossProgressView> getBossProgress() {
        return bossProgress;
    }

    public void setBossProgress(List<BossProgressView> bossProgress) {
        this.bossProgress = bossProgress;
    }
}

