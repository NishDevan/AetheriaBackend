package com.devan.aetheria.backend.dto;

import com.devan.aetheria.backend.entities.BossEntity;
import com.devan.aetheria.backend.entities.BossProgressEntity;

public class BossProgressView {
    private Long bossId;
    private String bossName;
    private Integer maxHealth;
    private Integer baseDamage;
    private Boolean isDefeated;

    public static BossProgressView fromEntity(BossProgressEntity entity) {
        BossProgressView view = new BossProgressView();
        BossEntity boss = entity.getBoss();
        view.bossId = boss.getId();
        view.bossName = boss.getName();
        view.maxHealth = boss.getMaxHealth();
        view.baseDamage = boss.getBaseDamage();
        view.isDefeated = entity.getIsDefeated();
        return view;
    }

    public Long getBossId() {
        return bossId;
    }

    public void setBossId(Long bossId) {
        this.bossId = bossId;
    }

    public String getBossName() {
        return bossName;
    }

    public void setBossName(String bossName) {
        this.bossName = bossName;
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

    public Boolean getIsDefeated() {
        return isDefeated;
    }

    public void setIsDefeated(Boolean defeated) {
        isDefeated = defeated;
    }
}

