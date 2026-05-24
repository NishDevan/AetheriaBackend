package com.devan.aetheria.backend.dto;

import com.devan.aetheria.backend.entities.WorldEntity;
import java.util.Date;

public class WorldView {
    private Long id;
    private String name;
    private String difficulty;
    private Long seed;
    private Long gameTime;
    private String biomeType;
    private Integer width;
    private Integer height;
    private Date createdAt;
    private Date lastPlayed;
    private Boolean isActive;

    public static WorldView fromEntity(WorldEntity entity) {
        WorldView view = new WorldView();
        view.id = entity.getId();
        view.name = entity.getName();
        view.difficulty = entity.getDifficulty();
        view.seed = entity.getSeed();
        view.gameTime = entity.getGameTime();
        view.biomeType = entity.getBiomeType();
        view.width = entity.getWidth();
        view.height = entity.getHeight();
        view.createdAt = entity.getCreatedAt();
        view.lastPlayed = entity.getLastPlayed();
        view.isActive = entity.getIsActive();
        return view;
    }

    // Getters and Setters
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

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public Long getSeed() {
        return seed;
    }

    public void setSeed(Long seed) {
        this.seed = seed;
    }

    public Long getGameTime() {
        return gameTime;
    }

    public void setGameTime(Long gameTime) {
        this.gameTime = gameTime;
    }

    public String getBiomeType() {
        return biomeType;
    }

    public void setBiomeType(String biomeType) {
        this.biomeType = biomeType;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getLastPlayed() {
        return lastPlayed;
    }

    public void setLastPlayed(Date lastPlayed) {
        this.lastPlayed = lastPlayed;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }
}

