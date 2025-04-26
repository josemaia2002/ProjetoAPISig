package com.spring.project.dto;

import jakarta.validation.constraints.NotEmpty;

public record DepartmentRequestDTO(
    @NotEmpty
    String name
) {}