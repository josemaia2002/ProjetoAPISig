package com.spring.project.service;

import java.util.List;

import com.spring.project.dto.MissionResponseDTO;

import jakarta.validation.constraints.Positive;

public interface EmployeeMissionService {
    List<MissionResponseDTO> findEmployeeMissions(@Positive Long employeeId);

    void assignMissionToEmployee(@Positive Long employeeId, @Positive Long missionId);

    void removeEmployeeFromMission(@Positive Long employeeId, @Positive Long missionId);
}