package com.devan.aetheria.backend.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class PlayerRequest {
    @NotBlank
    public String username;

    @NotNull
    @Min(0)
    public Integer health;

    @NotNull
    public Float locX;

    @NotNull
    public Float locY;

    @NotNull
    @Min(1)
    public Integer level;
}
