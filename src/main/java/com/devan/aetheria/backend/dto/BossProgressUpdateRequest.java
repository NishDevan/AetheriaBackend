package com.devan.aetheria.backend.dto;

import jakarta.validation.constraints.NotNull;

public class BossProgressUpdateRequest {
    @NotNull
    public Boolean isDefeated;
}

