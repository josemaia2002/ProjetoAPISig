package com.spring.project.service;

import java.util.List;

import com.spring.project.dto.EmployeeResponseDTO;
import com.spring.project.dto.MissionRequestDTO;
import com.spring.project.dto.MissionResponseDTO;
import com.spring.project.model.Mission;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

public interface MissionService {
    List<MissionResponseDTO> findAllMissions();

    MissionResponseDTO findMissionById(@Positive Long missionId);

    Mission findMissionEntityById(@Positive Long missionId);

    List<EmployeeResponseDTO> findMissionEmployees(@Positive Long missionId);

    void createMission(@Valid MissionRequestDTO missionRequestDTO);

    void updateMission(@Valid MissionRequestDTO missionRequestDTO, @Positive Long missionId);

    void deleteMission(@Positive Long missionId);
}