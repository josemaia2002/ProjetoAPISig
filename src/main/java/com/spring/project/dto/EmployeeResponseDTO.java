package com.spring.project.dto;

import java.time.LocalDate;
import java.util.List;

public record EmployeeResponseDTO(
    Long id,

    String firstName,

    String lastName,
    
    String email,

    LocalDate birthDate,

    AddressResponseDTO address,

    DepartmentResponseDTO department,

    List<MissionResponseDTO> missions
) {}