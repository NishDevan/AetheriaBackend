package com.devan.aetheria.backend.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class BossRequest {
    @NotBlank
    public String name;

    @NotNull
    @Min(1)
    public Integer maxHealth;

    @NotNull
    @Min(0)
    public Integer baseDamage;

    public Long specialDropId;
}
