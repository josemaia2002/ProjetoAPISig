package com.spring.project.dto;

public record AddressResponseDTO(
    Long id,

    String street,

    String houseNumber,

    String zipCode
) {}