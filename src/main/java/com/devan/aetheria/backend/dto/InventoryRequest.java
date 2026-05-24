package com.devan.aetheria.backend.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class InventoryRequest {
    @NotNull
    public Long playerId;

    @NotNull
    public Long itemId;

    @NotNull
    @Min(1)
    public Integer quantity;

    @NotNull
    @Min(0)
    public Integer slotIndex;
}
