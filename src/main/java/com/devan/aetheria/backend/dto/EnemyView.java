package com.devan.aetheria.backend.dto;

import com.devan.aetheria.backend.entities.EnemyEntity;
import com.devan.aetheria.backend.entities.ItemEntity;

public class EnemyView {
    private Long id;
    private String name;
    private Integer maxHealth;
    private Integer baseDamage;
    private Long dropItemId;
    private String dropItemName;

    public static EnemyView fromEntity(EnemyEntity entity) {
        EnemyView view = new EnemyView();
        view.id = entity.getId();
        view.name = entity.getName();
        view.maxHealth = entity.getMaxHealth();
        view.baseDamage = entity.getBaseDamage();
        ItemEntity dropItem = entity.getDropItem();
        if (dropItem != null) {
            view.dropItemId = dropItem.getId();
            view.dropItemName = dropItem.getItemName();
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

    public Long getDropItemId() {
        return dropItemId;
    }

    public void setDropItemId(Long dropItemId) {
        this.dropItemId = dropItemId;
    }

    public String getDropItemName() {
        return dropItemName;
    }

    public void setDropItemName(String dropItemName) {
        this.dropItemName = dropItemName;
    }
}

