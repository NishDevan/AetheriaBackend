package com.devan.aetheria.backend.dto;

import jakarta.validation.constraints.NotNull;

public class BossProgressRequest {
    @NotNull
    public Long playerId;

    @NotNull
    public Long bossId;

    @NotNull
    public Boolean isDefeated;
}
