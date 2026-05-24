package com.devan.aetheria.backend.dto;

import com.devan.aetheria.backend.entities.InventoryEntity;
import com.devan.aetheria.backend.entities.ItemEntity;

public class InventoryView {
    private Long id;
    private Long playerId;
    private Long itemId;
    private String itemName;
    private Integer itemType;
    private Float power;
    private Integer quantity;
    private Integer slotIndex;

    public static InventoryView fromEntity(InventoryEntity entity) {
        InventoryView view = new InventoryView();
        ItemEntity item = entity.getItem();
        view.id = entity.getId();
        view.playerId = entity.getPlayer().getId();
        view.itemId = item.getId();
        view.itemName = item.getItemName();
        view.itemType = item.getItemType();
        view.power = item.getPower();
        view.quantity = entity.getQuantity();
        view.slotIndex = entity.getSlotIndex();
        return view;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getSlotIndex() {
        return slotIndex;
    }

    public void setSlotIndex(Integer slotIndex) {
        this.slotIndex = slotIndex;
    }
}

