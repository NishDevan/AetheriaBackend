package com.devan.aetheria.backend.dto;

import com.devan.aetheria.backend.entities.BossEntity;
import com.devan.aetheria.backend.entities.ItemEntity;

public class BossView {
    private Long id;
    private String name;
    private Integer maxHealth;
    private Integer baseDamage;
    private Long specialDropId;
    private String specialDropName;

    public static BossView fromEntity(BossEntity entity) {
        BossView view = new BossView();
        view.id = entity.getId();
        view.name = entity.getName();
        view.maxHealth = entity.getMaxHealth();
        view.baseDamage = entity.getBaseDamage();
        ItemEntity specialDrop = entity.getSpecialDrop();
        if (specialDrop != null) {
            view.specialDropId = specialDrop.getId();
            view.specialDropName = specialDrop.getItemName();
        }
        return view;
    }

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

    public Long getSpecialDropId() {
        return specialDropId;
    }

    public void setSpecialDropId(Long specialDropId) {
        this.specialDropId = specialDropId;
    }

    public String getSpecialDropName() {
        return specialDropName;
    }

    public void setSpecialDropName(String specialDropName) {
        this.specialDropName = specialDropName;
    }
}

