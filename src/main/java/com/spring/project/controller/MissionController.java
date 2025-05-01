package com.spring.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.spring.project.dto.EmployeeResponseDTO;
import com.spring.project.dto.MissionRequestDTO;
import com.spring.project.dto.MissionResponseDTO;
import com.spring.project.service.MissionService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("/api/missions")
@Validated
public class MissionController {
    private MissionService missionService;

    @Autowired
    public MissionController(MissionService missionService) {
        this.missionService = missionService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/")
    public List<MissionResponseDTO> findAllMissions() {
        return missionService.findAllMissions();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{missionId}")
    public MissionResponseDTO findMissionById(@PathVariable @Positive Long missionId) {
        return missionService.findMissionById(missionId);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{missionId}/employees")
    public List<EmployeeResponseDTO> findMissionEmployees(@PathVariable @Positive Long missionId) {
        return missionService.findMissionEmployees(missionId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/")
    public void createMission(@Valid @RequestBody MissionRequestDTO missionRequestDTO) {
        missionService.createMission(missionRequestDTO);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping(value = "/{missionId}")
    public void updatedMission(@Valid @RequestBody MissionRequestDTO missionRequestDTO, @PathVariable @Positive Long missionId) {
        missionService.updateMission(missionRequestDTO, missionId);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{missionId}")
    public void deleteMission(@PathVariable @Positive Long missionId) {
        missionService.deleteMission(missionId);
    }
}