package com.devan.aetheria.backend.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public class ItemRequest {
    @NotBlank
    public String itemName;

    @NotNull
    @Min(0)
    public Integer itemType;

    @NotNull
    @PositiveOrZero
    public Float power;
}
