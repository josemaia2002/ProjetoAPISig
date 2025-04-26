package com.spring.project.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

public record MissionRequestDTO(
    @NotEmpty
    String name,

    @Positive
    int duration
) {}