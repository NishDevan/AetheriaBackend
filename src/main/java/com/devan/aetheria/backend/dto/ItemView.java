package com.devan.aetheria.backend.dto;

import com.devan.aetheria.backend.entities.ItemEntity;

public class ItemView {
    private Long id;
    private String itemName;
    private Integer itemType;
    private Float power;

    public static ItemView fromEntity(ItemEntity entity) {
        ItemView view = new ItemView();
        view.id = entity.getId();
        view.itemName = entity.getItemName();
        view.itemType = entity.getItemType();
        view.power = entity.getPower();
        return view;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Integer getItemType() {
        return itemType;
    }

    public void setItemType(Integer itemType) {
        this.itemType = itemType;
    }

    public Float getPower() {
        return power;
    }

    public void setPower(Float power) {
        this.power = power;
    }
}

