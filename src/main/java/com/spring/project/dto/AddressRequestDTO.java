package com.spring.project.dto;

import jakarta.validation.constraints.NotEmpty;

public record AddressRequestDTO(
    @NotEmpty
    String street,

    @NotEmpty
    String houseNumber,

    @NotEmpty
    String zipCode
) {}