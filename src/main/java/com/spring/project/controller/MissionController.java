package com.spring.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

@RestController
@RequestMapping("/api/missions")
public class MissionController {
    @Autowired
    private MissionService missionService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/")
    public List<MissionResponseDTO> findAllMissions() {
        return missionService.findAllMissions();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{id}")
    public MissionResponseDTO findMissionById(@PathVariable Long missionId) {
        return missionService.findMissionById(missionId);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{id}/employees")
    public List<EmployeeResponseDTO> findMissionEmployees(@PathVariable Long missionId) {
        return missionService.findMissionEmployees(missionId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/")
    public void createMission(@RequestBody MissionRequestDTO missionRequestDTO) {
        missionService.createMission(missionRequestDTO);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping(value = "/{id}")
    public void updatedMission(@RequestBody MissionRequestDTO missionRequestDTO, @PathVariable Long missionId) {
        missionService.updateMission(missionRequestDTO, missionId);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{id}")
    public void deleteMission(@PathVariable Long missionId) {
        missionService.deleteMission(missionId);
    }
}