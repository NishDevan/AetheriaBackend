package com.devan.aetheria.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class WorldRequest {
    @NotBlank(message = "World name is required")
    public String name;

    // Difficulty is optional, defaults to NORMAL if not provided
    @Pattern(regexp = "^(EASY|NORMAL|HARD)$", message = "Difficulty must be EASY, NORMAL, or HARD")
    public String difficulty; // Can be null, defaults to NORMAL in service

    public Long seed; // Can be null, will be randomly generated

    public String biomeType; // Can be null, will be randomly selected

    public Integer width = 120;
    public Integer height = 60;
}

