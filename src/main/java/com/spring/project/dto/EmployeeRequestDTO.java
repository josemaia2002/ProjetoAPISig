package com.spring.project.dto;

import java.time.LocalDate;
import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Past;

public record EmployeeRequestDTO(
    @NotEmpty
    String firstName,

    @NotEmpty
    String lastName,

    @Email
    String email,

    @Past
    LocalDate birthDate,

    @Valid
    AddressRequestDTO address,
    
    Long departmentId,

    List<Long> missionIds
) {}